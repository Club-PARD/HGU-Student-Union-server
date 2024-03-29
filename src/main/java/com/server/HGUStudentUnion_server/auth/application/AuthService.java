package com.server.HGUStudentUnion_server.auth.application;

import com.server.HGUStudentUnion_server.appUser.application.AppUserService;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.appUser.domain.repository.AppUserRepo;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AppUserRequest;
import com.server.HGUStudentUnion_server.auth.application.dto.LoginRequestDto;
import com.server.HGUStudentUnion_server.auth.application.dto.LoginResponseDto;
import com.server.HGUStudentUnion_server.auth.domain.LoginUser;
import com.server.HGUStudentUnion_server.auth.domain.Member;
import com.server.HGUStudentUnion_server.auth.domain.OauthUserInfo;
import com.server.HGUStudentUnion_server.auth.infrastructure.JwtProvider;
import com.server.HGUStudentUnion_server.auth.infrastructure.OauthHandler;
import com.server.HGUStudentUnion_server.common.DataEnDecryption;
import com.server.HGUStudentUnion_server.exception.oauth.InvalidTokenException;
import com.server.HGUStudentUnion_server.exception.oauth.ManagerNoAuthorizationException;
import com.server.HGUStudentUnion_server.exception.oauth.NoAuthorizationException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final OauthHandler oauthHandler;

    private final AppUserService appUserService;
    private final AppUserRepo appUserRepo;
    private final JwtProvider jwtProvider;

    @Value("${EncSecretKey}")
    private String secretKey;
    @Value("${EncIv}")
    private String iv;

    public OauthUserInfo getStudentInfo(LoginRequestDto loginRequestDto) {
        String oauthProvider = loginRequestDto.getOauthProvider();
        return oauthHandler.getUserInfoFromCode(oauthProvider, loginRequestDto.getCode(), Member.NORMAL);
    }
    public OauthUserInfo getAdminInfo(LoginRequestDto loginRequestDto) {
        String oauthProvider = loginRequestDto.getOauthProvider();
        return oauthHandler.getUserInfoFromCode(oauthProvider, loginRequestDto.getCode(), Member.MANAGER);
    }
    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        OauthUserInfo userInfo =  getStudentInfo(loginRequestDto);
        String encryptedEmail = DataEnDecryption.encrypt(userInfo.getEmail(), secretKey, iv);
        Optional<AppUser> appUser = appUserRepo.findByEmail(encryptedEmail);
        return appUser.map(value -> {
            value.updateLoginTime();
            if(value.isManager()){
                return new LoginResponseDto(
                        jwtProvider.createToken(String.valueOf(value.getId()), Member.MANAGER));
            }else {
                return new LoginResponseDto(
                        jwtProvider.createToken(String.valueOf(value.getId()), Member.NORMAL));
            }
        }).orElseGet(()-> {
            AppUser val = appUserRepo.save(AppUser.from(new AppUserRequest(1, userInfo.getName(), DataEnDecryption.encrypt(userInfo.getEmail(), secretKey, iv))));
            return new LoginResponseDto(
                    jwtProvider.createToken(String.valueOf(val.getId()), Member.NORMAL));
        });
    }
    @Transactional
    public LoginResponseDto adminLogin(LoginRequestDto loginRequestDto) {
        OauthUserInfo userInfo =  getAdminInfo(loginRequestDto);
        String encryptedEmail = DataEnDecryption.encrypt(userInfo.getEmail(), secretKey, iv);
        Optional<AppUser> appUser = appUserRepo.findByEmail(encryptedEmail);
        return appUser.map(value -> {

            if(value.isManager()){
                value.updateLoginTime();
                return new LoginResponseDto(
                        jwtProvider.createToken(String.valueOf(value.getId()), Member.MANAGER));
            }else {
                return new LoginResponseDto(null);
            }
        }).orElseGet(()-> {
            AppUser val = appUserRepo.save(AppUser.from(new AppUserRequest(1, userInfo.getName(),DataEnDecryption.encrypt(userInfo.getEmail(), secretKey, iv))));
            return new LoginResponseDto(null);
        });
    }

    @Transactional(readOnly = true)
    public AppUser findSuperManagerByToken(String token) {
        if (!jwtProvider.isValidToken(token, Member.MANAGER)) {
            throw new InvalidTokenException();
        }
        String payLoad = jwtProvider.getPayLoad(token, Member.MANAGER);
        Long id = Long.parseLong(payLoad);
        AppUser appUser = appUserService.findById(id);
        if (!appUser.isSuperManager())
            throw new ManagerNoAuthorizationException();
        return appUser;
    }
    @Transactional(readOnly = true)
    public AppUser findSUManagerByToken(String token) {
        if (!jwtProvider.isValidToken(token, Member.MANAGER)) {
            throw new InvalidTokenException();
        }
        String payLoad = jwtProvider.getPayLoad(token, Member.MANAGER);
        Long id = Long.parseLong(payLoad);
        AppUser appUser = appUserService.findById(id);
        if (!appUser.isSUManager())
            throw new ManagerNoAuthorizationException();
        return appUser;
    }
    @Transactional(readOnly = true)
    public AppUser findManagerByToken(String token) {
        if (!jwtProvider.isValidToken(token, Member.MANAGER)) {
            throw new InvalidTokenException();
        }
        String payLoad = jwtProvider.getPayLoad(token, Member.MANAGER);
        Long id = Long.parseLong(payLoad);
        AppUser appUser = appUserService.findById(id);
        if (!appUser.isManager())
            throw new ManagerNoAuthorizationException();
        return appUser;
    }
    @Transactional(readOnly = true)
    public AppUser findNormalByToken(String token) {
        if(!jwtProvider.validateBothToken(token)){
            throw new InvalidTokenException();
        }
//        if (!jwtProvider.isValidToken(token, Member.NORMAL)) {
//                throw new InvalidTokenException();
//        }
        String payLoad;
        try{
            payLoad = jwtProvider.getPayLoad(token, Member.NORMAL);
        } catch (SignatureException e){
            try{
                payLoad = jwtProvider.getPayLoad(token, Member.MANAGER);
            } catch (JwtException | IllegalArgumentException e2){
                throw new InvalidTokenException();
            }
        }
        Long id = Long.parseLong(payLoad);
        AppUser appUser = appUserService.findById(id);
        if (!appUser.isNormal())
            throw new NoAuthorizationException();
        return appUser;
    }
    @Transactional(readOnly = true)
    public void checkSuperManagerByToken(String token) {
        AppUser appUser = findSuperManagerByToken(token);
        if (!appUser.isSuperManager())
            throw new ManagerNoAuthorizationException();
    }
    @Transactional(readOnly = true)
    public void checkSUManagerByToken(String token) {
        AppUser appUser = findSUManagerByToken(token);
        if (!appUser.isSUManager())
            throw new ManagerNoAuthorizationException();
    }
    @Transactional(readOnly = true)
    public void checkManagerByToken(String token) {
        AppUser appUser = findManagerByToken(token);
        if (!appUser.isNormal())
            throw new ManagerNoAuthorizationException();
    }
    @Transactional(readOnly = true)
    public void checkNormalByToken(String token) {
        AppUser appUser = findNormalByToken(token);
        if (!appUser.isNormal())
            throw new NoAuthorizationException();
    }


    @Transactional(readOnly = true)
    public LoginUser getLoginNormal(String accessToken) {
        AppUser appUser = findNormalByToken(accessToken);
        if(!appUser.isNormal())
            throw new NoAuthorizationException();
        return new LoginUser(appUser.getId());
    }

    @Transactional(readOnly = true)
    public LoginUser getLoginManager(String accessToken) {
        AppUser appUser = findManagerByToken(accessToken);
        if(!appUser.isManager())
            throw new ManagerNoAuthorizationException();
        return new LoginUser(appUser.getId());
    }

    @Transactional(readOnly = true)
    public LoginUser getLoginSUManager(String accessToken) {
        AppUser appUser = findSUManagerByToken(accessToken);
        if(!appUser.isSUManager())
            throw new ManagerNoAuthorizationException();
        return new LoginUser(appUser.getId());
    }

    @Transactional(readOnly = true)
    public LoginUser getLoginSuperManager(String accessToken) {
        AppUser appUser = findSuperManagerByToken(accessToken);
        if(!appUser.isSuperManager())
            throw new ManagerNoAuthorizationException();
        return new LoginUser(appUser.getId());
    }
}
