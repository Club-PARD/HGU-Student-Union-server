package com.server.HGUStudentUnion_server.notice.presentation;

import com.server.HGUStudentUnion_server.attachFile.application.AttachFileService;
import com.server.HGUStudentUnion_server.attachFile.domain.AttachFile;
import com.server.HGUStudentUnion_server.auth.domain.LoginUser;
import com.server.HGUStudentUnion_server.auth.domain.logins.ManagerLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredManagerLogin;
import com.server.HGUStudentUnion_server.notice.application.NoticeService;
import com.server.HGUStudentUnion_server.notice.domain.Notice;
import com.server.HGUStudentUnion_server.notice.presentation.request.NoticeRequest;
import com.server.HGUStudentUnion_server.notice.presentation.request.NoticeUpdateRequest;
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

    @Autowired
    private AttachFileService attachFileService;

    @GetMapping("/notices")
    public ResponseEntity<List<Notice>> findAll (){
        List<Notice> res = noticeService.findAll();
        return ResponseEntity.ok(res);
    }
    @GetMapping("/notices/{noticeId}")
    public ResponseEntity<Notice> findById (@PathVariable Long noticeId){
        Notice res = noticeService.find(noticeId);
        return ResponseEntity.ok(res);
    }
    @PostMapping("/notices")
    @RequiredManagerLogin
    public ResponseEntity<Notice> save (@ManagerLogin LoginUser loginUser, @RequestBody NoticeRequest request){
        Notice res = noticeService.save(loginUser, request);
        return ResponseEntity.ok(res);
    }
    @PatchMapping("/notices/{noticeId}")
    @RequiredManagerLogin
    public ResponseEntity<Notice> update (@PathVariable Long noticeId, @RequestBody NoticeUpdateRequest request){
        Notice res = noticeService.findById(noticeId);
        if(request.getDelFiles().size()>0){
            attachFileService.deleteById(request.getDelFiles());
        }
        List<AttachFile> newFiles = new ArrayList<>();
        if(request.getUrls().size()>0) {
            newFiles = attachFileService.saveMultiNoticeFile(request.getUrls(), request.getTitles(), res);
        }
        return ResponseEntity.ok(noticeService.update(noticeId, request, newFiles));
    }
    @DeleteMapping("/notices/{noticeId}")
    @RequiredManagerLogin
    public ResponseEntity<Long> delete (@PathVariable Long noticeId){
        noticeService.delete(noticeId);
        return ResponseEntity.ok(noticeId);
    }
}
