package com.server.HGUStudentUnion_server.appUser.presentation;

import com.server.HGUStudentUnion_server.appUser.application.AppUserService;
import com.server.HGUStudentUnion_server.appUser.domain.Admin;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.appUser.domain.repository.AdminRepo;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AdminRequest;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AppUserRequest;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AuthRequest;
import com.server.HGUStudentUnion_server.appUser.presentation.response.AppUserProfile;
import com.server.HGUStudentUnion_server.auth.domain.LoginUser;
import com.server.HGUStudentUnion_server.auth.domain.logins.ManagerLogin;
import com.server.HGUStudentUnion_server.auth.domain.logins.NormalLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredManagerLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredSuperManagerLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;



    @GetMapping("/appUsers")
    @RequiredManagerLogin
    public ResponseEntity<List<AppUser>> findAll(){
        List<AppUser> res = appUserService.findAll();
        return ResponseEntity.ok(res);
    }
    @GetMapping("/appUsers/{appUserId}")
    @RequiredManagerLogin
    public ResponseEntity<AppUser> find(@PathVariable Long appUserId){
        AppUser res = appUserService.find(appUserId);
        return ResponseEntity.ok(res);
    }
    @GetMapping("/appUsers/myProfile")
    @RequiredLogin
    public ResponseEntity<AppUserProfile> findMyProfile(@NormalLogin LoginUser loginUser){
        AppUserProfile res = appUserService.findMyProfile(loginUser.getId());
        return ResponseEntity.ok(res);
    }
//    @PostMapping("/appUsers")
//    public ResponseEntity<AppUser> save(@RequestBody AppUserRequest request){
//        AppUser res = appUserService.save(request);
//        return ResponseEntity.ok(res);
//    }

    // API 명세 추가
    @PatchMapping("/appUsers/auth/{appUserId}")
    @RequiredSuperManagerLogin
    public ResponseEntity<AppUser> updateAuth(@PathVariable Long appUserId, @RequestBody AuthRequest request){
        AppUser res = appUserService.updateAuth(appUserId, request);
        return ResponseEntity.ok(res);
    }
    // API 명세 추가
    @PatchMapping("/appUsers/updateProfile")
    @RequiredManagerLogin
    public ResponseEntity<AppUser> updateAdminProfile(@ManagerLogin LoginUser loginUser,@RequestBody AdminRequest request){
        AppUser res = appUserService.updateAdminProfile(loginUser.getId(), request);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/appUsers/{appUserId}")
    @RequiredSuperManagerLogin
    public ResponseEntity<Long> delete(@PathVariable Long appUserId){
        appUserService.delete(appUserId);
        return ResponseEntity.ok(appUserId);
    }

}
