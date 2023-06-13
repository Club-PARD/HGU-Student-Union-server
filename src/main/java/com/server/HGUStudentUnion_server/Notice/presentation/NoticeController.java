package com.server.HGUStudentUnion_server.Notice.presentation;

import com.server.HGUStudentUnion_server.Notice.application.NoticeService;
import com.server.HGUStudentUnion_server.Notice.domain.Notice;
import com.server.HGUStudentUnion_server.Notice.presentation.request.NoticeRequest;
import com.server.HGUStudentUnion_server.Notice.presentation.request.NoticeUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> findAll (){
        List<Notice> res = new ArrayList<>();
        res = noticeService.findAll();
        return ResponseEntity.ok(res);
    }
    @GetMapping("/notices/{noticeId}")
    public ResponseEntity<Notice> findById (@PathVariable Long noticeId){
        Notice res = new Notice();
        res = noticeService.find(noticeId);
        return ResponseEntity.ok(res);
    }
    @PostMapping("/notices")
    public ResponseEntity<Notice> save (@RequestBody NoticeRequest request){
        Notice res = new Notice();
        res = noticeService.save(request);
        return ResponseEntity.ok(res);
    }
    @PatchMapping("/notices/{noticeId}")
    public ResponseEntity<Notice> update (@PathVariable Long noticeId, @RequestBody NoticeUpdateRequest request){
        Notice res = new Notice();
        res = noticeService.update(noticeId, request);
        return ResponseEntity.ok(res);
    }
    @DeleteMapping("/notices/{noticeId}")
    public ResponseEntity<Long> delete (@PathVariable Long noticeId){
        noticeService.delete(noticeId);
        return ResponseEntity.ok(noticeId);
    }
}
