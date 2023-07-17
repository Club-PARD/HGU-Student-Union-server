package com.server.HGUStudentUnion_server.suggest.presentation;

import com.server.HGUStudentUnion_server.appUser.application.AppUserService;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.suggest.application.SuggestService;
import com.server.HGUStudentUnion_server.suggest.domain.Suggest;
import com.server.HGUStudentUnion_server.suggest.domain.SuggestAppUser;
import com.server.HGUStudentUnion_server.suggest.presentation.request.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SuggestController {

    @Autowired
    private SuggestService suggestService;
    @Autowired
    private AppUserService appUserService;

    @GetMapping("/suggests")
    public ResponseEntity<List<Suggest>> findAll(){
        return ResponseEntity.ok(suggestService.findAll());
    }
    @GetMapping("/suggests/{id}")
    public ResponseEntity<Suggest> find(@PathVariable Long id){
        return ResponseEntity.ok(suggestService.find(id));
    }

    @PostMapping("/suggests")
    public ResponseEntity<Suggest> save(@RequestBody SuggestRequest request){
        AppUser writer = appUserService.find(request.getUserId());
        return ResponseEntity.ok(suggestService.save(writer, request));
    }

    @PatchMapping("/suggests/{suggestId}")
    public ResponseEntity<Suggest> update(@PathVariable Long suggestId, @RequestBody SuggestUpdateRequest request){
        return ResponseEntity.ok(suggestService.update(suggestId, request));
    }
    @DeleteMapping("/suggests/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id){
        suggestService.delete(id);
        return ResponseEntity.ok(id);
    }
    @PatchMapping("/suggests/{suggestId}/status")
    public ResponseEntity<Suggest> status(@PathVariable Long suggestId, @RequestBody SuggestStatusRequest request){
        return ResponseEntity.ok(suggestService.status(suggestId, request));
    }
    @PatchMapping("/suggests/{suggestId}/answer")
    public ResponseEntity<Suggest> status(@PathVariable Long suggestId, @RequestBody SuggestAnswerRequest request){
        AppUser ansUser = appUserService.find((request.getAnsUserId()));
        return ResponseEntity.ok(suggestService.answer(suggestId, ansUser, request));
    }



//    @PostMapping("/suggests/recommend")
//    public ResponseEntity<SuggestAppUser> recommend(@RequestBody RecommendRequest request){
//        AppUser recommendUser = appUserService.find(request.getUserId());
//        return ResponseEntity.ok(suggestService.recommend(recommendUser, request));
//    }





}
