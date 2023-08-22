package com.server.HGUStudentUnion_server.appUser.application;

import com.server.HGUStudentUnion_server.appUser.domain.Admin;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.appUser.domain.repository.AdminRepo;
import com.server.HGUStudentUnion_server.appUser.domain.repository.AppUserRepo;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AdminRequest;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AppUserRequest;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AuthRequest;
import com.server.HGUStudentUnion_server.appUser.presentation.response.AppUserProfile;
import com.server.HGUStudentUnion_server.exception.AppUser.AppUserNotFoundException;
import com.server.HGUStudentUnion_server.suggest.domain.Suggest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private AdminRepo adminRepo;


    @Transactional
    public AppUser findById(Long userId) {
        return appUserRepo.findById(userId).orElseThrow(AppUserNotFoundException::new);
    }

    @Transactional
    public AppUser find(Long userId){
        return this.findById(userId);
    }

    @Transactional(readOnly = true)
    public List<AppUser> findAll() {
        return appUserRepo.findAll();
    }

    @Transactional
    public AppUser save(AppUserRequest request) {
        AppUser ret = appUserRepo.save(AppUser.from(request));
        return ret;
    }


    @Transactional
    public void delete(Long appUserId) {
        AppUser del = this.findById(appUserId);
        appUserRepo.delete(del);
    }

//    @Transactional
//    public AppUser update(Long appUserId, AppUserRequest request) {
//        AppUser ret = this.findById(appUserId);
//        ret.update(request);
//        return ret;
//    }

    @Transactional
    public Admin newAdmin() {
        return adminRepo.save(Admin.builder().position("미등록").department("미등록").build());
    }

    @Transactional
    public void deleteAdmin(Long id) {
        adminRepo.deleteById(id);
    }

    @Transactional
    public AppUser updateAdminProfile(Long appUserId, AdminRequest request) {
        AppUser ret = this.findById(appUserId);
        ret.updateProfile(request);
        Admin ad = adminRepo.findById(ret.getAdmin().getId()).get();
        ad.update(request);
        return ret;
    }

    @Transactional
    public AppUser updateAuth(Long appUserId, AuthRequest request) {
        AppUser ret = appUserRepo.findById(appUserId).orElseThrow(AppUserNotFoundException::new);
        if(ret.getAuth() == 1 && request.getAuth() > 1){
            // 일반유저 -> Admin
            Admin admin = this.newAdmin();
            ret.setAdmin(admin);
            ret.updateAuth(request);
        }
        else if(ret.getAuth() > 1 && request.getAuth() == 1){
            // Admin -> 일반 유저
            this.deleteAdmin(ret.getAdmin().getId());
            ret.setAdmin(null);
            ret.updateAuth(request);
        } else {
            ret.updateAuth(request);
        }
        return ret;
    }

    @Transactional(readOnly = true)
    public AppUserProfile findMyProfile(Long id) {
        AppUser temp = appUserRepo.findById(id).orElseThrow(AppUserNotFoundException::new);
        AppUserProfile ret = AppUserProfile.of(temp);
        return ret;
    }
}
