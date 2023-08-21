package com.server.HGUStudentUnion_server.shop.presentation;

import com.server.HGUStudentUnion_server.auth.domain.logins.SUManagerLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredSUManagerLogin;
import com.server.HGUStudentUnion_server.event.domain.Event;
import com.server.HGUStudentUnion_server.event.presentation.request.EventRequest;
import com.server.HGUStudentUnion_server.event.presentation.request.EventUpdateRequest;
import com.server.HGUStudentUnion_server.shop.application.ShopService;
import com.server.HGUStudentUnion_server.shop.domain.Shop;
import com.server.HGUStudentUnion_server.shop.presentation.request.ShopRequest;
import com.server.HGUStudentUnion_server.shop.presentation.request.ShopUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/shops")
    public ResponseEntity<List<Shop>> findAll(){
        List<Shop> res = shopService.findAll();
        return ResponseEntity.ok(res);
    }
    @GetMapping("/shops/{shopId}")
    public ResponseEntity<Shop> find(@PathVariable Long shopId){
        return ResponseEntity.ok(shopService.findById(shopId));
    }

    @PostMapping("/shops")
    @RequiredSUManagerLogin
    public ResponseEntity<Shop> save(@RequestBody ShopRequest request){
        Shop res = shopService.save(request);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/shops/{shopId}")
    @RequiredSUManagerLogin
    public ResponseEntity<Shop> update(@PathVariable Long shopId, @RequestBody ShopUpdateRequest request){
        Shop res = shopService.update(shopId, request);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/shops/{shopId}")
    @RequiredSUManagerLogin
    public ResponseEntity<Long> delete(@PathVariable Long shopId){
        shopService.delete(shopId);
        return ResponseEntity.ok(shopId);
    }

}
