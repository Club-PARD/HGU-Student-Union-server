package com.server.HGUStudentUnion_server.banner.presentation;

import com.server.HGUStudentUnion_server.auth.domain.required.RequiredSuperManagerLogin;
import com.server.HGUStudentUnion_server.banner.application.BannerService;
import com.server.HGUStudentUnion_server.banner.domain.Banner;
import com.server.HGUStudentUnion_server.banner.domain.PopUp;
import com.server.HGUStudentUnion_server.banner.presentation.request.BannerRequest;
import com.server.HGUStudentUnion_server.popUp.presentation.request.PopUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/banners")
    public ResponseEntity<List<Banner>> findAll(){
        return ResponseEntity.ok(bannerService.findAll());
    }
    @GetMapping("/banners/{id}")
    public ResponseEntity<Banner> findById(@PathVariable Long id){
        return ResponseEntity.ok(bannerService.findById(id));
    }
    @PostMapping("/banners")
    @RequiredSuperManagerLogin
    public ResponseEntity<Banner> save(@RequestBody BannerRequest request){
        Banner res = bannerService.save(request);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/banners/{id}")
    @RequiredSuperManagerLogin
    public ResponseEntity<Banner> update(@PathVariable Long id,@RequestBody BannerRequest request){
        return ResponseEntity.ok(bannerService.update(id, request));
    }
    @DeleteMapping("/banners/{id}")
    @RequiredSuperManagerLogin
    public ResponseEntity<Long> delete(@PathVariable Long id){
        bannerService.delete(id);
        return ResponseEntity.ok(id);
    }


}
