package com.server.HGUStudentUnion_server.suggest.presentation;

import com.server.HGUStudentUnion_server.appUser.application.AppUserService;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.auth.domain.LoginUser;
import com.server.HGUStudentUnion_server.auth.domain.logins.NormalLogin;
import com.server.HGUStudentUnion_server.auth.domain.logins.SUManagerLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredSUManagerLogin;
import com.server.HGUStudentUnion_server.suggest.application.SuggestService;
import com.server.HGUStudentUnion_server.suggest.domain.Suggest;
import com.server.HGUStudentUnion_server.suggest.domain.SuggestAppUser;
import com.server.HGUStudentUnion_server.suggest.presentation.request.*;
import com.server.HGUStudentUnion_server.suggest.presentation.response.SuggestDetailResponse;
import com.server.HGUStudentUnion_server.suggest.presentation.response.SuggestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SuggestController {

    @Autowired
    private SuggestService suggestService;
    @Autowired
    private AppUserService appUserService;

    @GetMapping("/suggests")
    @RequiredLogin
    public ResponseEntity<List<SuggestResponse>> findAll(){
        return ResponseEntity.ok(suggestService.findAll());
    }
    @GetMapping("/suggests/{id}")
    @RequiredLogin
    public ResponseEntity<SuggestDetailResponse> find(@NormalLogin LoginUser loginUser, @PathVariable Long id){
        return ResponseEntity.ok(suggestService.find(id, loginUser.getId()));
    }

    @PostMapping("/suggests")
    @RequiredLogin
    public ResponseEntity<Suggest> save(@NormalLogin LoginUser loginUser ,@RequestBody SuggestRequest request){
        return ResponseEntity.ok(suggestService.save(loginUser.getId(), request));
    }

    @PatchMapping("/suggests/{suggestId}")
    @RequiredSUManagerLogin
    public ResponseEntity<Suggest> update(@PathVariable Long suggestId, @RequestBody SuggestUpdateRequest request){
        return ResponseEntity.ok(suggestService.update(suggestId, request));
    }
    @DeleteMapping("/suggests/{id}")
    @RequiredSUManagerLogin
    public ResponseEntity<Long> delete(@PathVariable Long id){
        suggestService.delete(id);
        return ResponseEntity.ok(id);
    }
    @PatchMapping("/suggests/{suggestId}/status")
    @RequiredSUManagerLogin
    public ResponseEntity<Suggest> status(@PathVariable Long suggestId, @RequestBody SuggestStatusRequest request){
        return ResponseEntity.ok(suggestService.status(suggestId, request));
    }
    @PatchMapping("/suggests/{suggestId}/answer")
    @RequiredSUManagerLogin
    public ResponseEntity<Suggest> answer(@SUManagerLogin LoginUser loginUser, @PathVariable Long suggestId, @RequestBody SuggestAnswerRequest request){
        AppUser ansUser = appUserService.find(loginUser.getId());
        return ResponseEntity.ok(suggestService.answer(suggestId, ansUser, request));
    }
    @PostMapping("/suggests/{suggestId}/recommend")
    @RequiredLogin
    public ResponseEntity<Long> recommend(@NormalLogin LoginUser loginUser ,@PathVariable Long suggestId){
        AppUser recommendUser = appUserService.find(loginUser.getId());
        return ResponseEntity.ok(suggestService.recommend(recommendUser, suggestId));
    }





}
