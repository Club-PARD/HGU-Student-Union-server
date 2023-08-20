package com.server.HGUStudentUnion_server.appUser.presentation;

import com.server.HGUStudentUnion_server.appUser.application.AppUserService;
import com.server.HGUStudentUnion_server.appUser.domain.Admin;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.appUser.domain.repository.AdminRepo;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AdminRequest;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AppUserRequest;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AuthRequest;
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
    public ResponseEntity<List<AppUser>> findAll(){
        List<AppUser> res = appUserService.findAll();
        return ResponseEntity.ok(res);
    }
    @GetMapping("/appUsers/{appUserId}")
    public ResponseEntity<AppUser> find(@PathVariable Long appUserId){
        AppUser res = appUserService.find(appUserId);
        return ResponseEntity.ok(res);
    }
    @PostMapping("/appUsers")
    public ResponseEntity<AppUser> save(@RequestBody AppUserRequest request){
        AppUser res = appUserService.save(request);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/appUsers/{appUserId}")
    public ResponseEntity<AppUser> update(@PathVariable Long appUserId, @RequestBody AppUserRequest request){
        AppUser res = appUserService.update(appUserId, request);
        return ResponseEntity.ok(res);
    }
    // API 명세 추가
    @PatchMapping("/appUsers/auth/{appUserId}")
    public ResponseEntity<AppUser> updateAuth(@PathVariable Long appUserId, @RequestBody AuthRequest request){
        AppUser res = appUserService.find(appUserId);
        if(res.getAuth() == 1 && request.getAuth() > 1){
            // 일반유저 -> Admin
            Admin admin = appUserService.newAdmin();
            res.setAdmin(admin);
            res.updateAuth(request);
        }
        else if(res.getAuth() > 1 && request.getAuth() == 1){
            // Admin -> 일반 유저
            appUserService.deleteAdmin(res.getAdmin().getId());
            res.setAdmin(null);
            res.updateAuth(request);
        } else {
            res.updateAuth(request);
        }
        return ResponseEntity.ok(res);
    }
    // API 명세 추가
    @PatchMapping("/appUsers/updateProfile/{appUserId}")
    public ResponseEntity<AppUser> updateAdminProfile(@PathVariable Long appUserId, AdminRequest request){
        AppUser res = appUserService.updateAdminProfile(appUserId, request);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/appUsers/{appUserId}")
    public ResponseEntity<Long> delete(@PathVariable Long appUserId){
        appUserService.delete(appUserId);
        return ResponseEntity.ok(appUserId);
    }

}
