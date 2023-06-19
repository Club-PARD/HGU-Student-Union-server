package com.server.HGUStudentUnion_server.inquiry.presentation;

import com.server.HGUStudentUnion_server.appUser.application.AppUserService;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.inquiry.application.InquiryService;
import com.server.HGUStudentUnion_server.inquiry.domain.Inquiry;
import com.server.HGUStudentUnion_server.inquiry.presentation.request.AnswerRequest;
import com.server.HGUStudentUnion_server.inquiry.presentation.request.InquiryRequest;
import com.server.HGUStudentUnion_server.inquiry.presentation.request.InquiryStatus;
import com.server.HGUStudentUnion_server.inquiry.presentation.request.InquiryUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;
    @Autowired
    private AppUserService appUserService;

    @GetMapping("/inquiries")
    public ResponseEntity<List<Inquiry>> findAll(){
        return ResponseEntity.ok(inquiryService.findAll());
    }
    @GetMapping("/inquiries/{id}")
    public ResponseEntity<Inquiry> findById(@PathVariable Long id){
        return ResponseEntity.ok(inquiryService.find(id));
    }
    @PostMapping("/inquiries")
    public ResponseEntity<Inquiry> save(@RequestBody InquiryRequest request){
        AppUser writer = appUserService.find(request.getUserId());
        Inquiry res = inquiryService.save(writer, request);
        return ResponseEntity.ok(res);
    }
    @PatchMapping("/inquiries/{id}")
    public ResponseEntity<Inquiry> update(@PathVariable Long id, @RequestBody InquiryUpdateRequest request){
        return ResponseEntity.ok(inquiryService.update(id, request));
    }

    @DeleteMapping("/inquiries/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id){
        inquiryService.delete(id);
        return ResponseEntity.ok(id);
    }

    @PatchMapping("/inquiries/{id}/status")
    public ResponseEntity<Inquiry> updateStatus(@PathVariable Long id, @RequestBody InquiryStatus request){
        return ResponseEntity.ok(inquiryService.updateStatus(id, request.getStatus()));
    }

    @PatchMapping("/inquiries/{id}/answer")
    public ResponseEntity<Inquiry> answer(@PathVariable Long id, @RequestBody AnswerRequest request){
        AppUser ansUser = appUserService.find(request.getUserId());
        return ResponseEntity.ok(inquiryService.answer(id, ansUser, request));
    }








}
