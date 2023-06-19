package com.server.HGUStudentUnion_server.appUser.presentation;

import com.server.HGUStudentUnion_server.appUser.application.AppUserService;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AppUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/appUsers/{appUserId}")
    public ResponseEntity<Long> delete(@PathVariable Long appUserId){
        appUserService.delete(appUserId);
        return ResponseEntity.ok(appUserId);
    }

}
