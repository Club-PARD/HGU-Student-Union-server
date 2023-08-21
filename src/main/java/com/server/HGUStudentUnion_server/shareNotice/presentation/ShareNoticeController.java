package com.server.HGUStudentUnion_server.shareNotice.presentation;

import com.server.HGUStudentUnion_server.appUser.application.AppUserService;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.attachFile.application.AttachFileService;
import com.server.HGUStudentUnion_server.attachFile.domain.AttachFile;
import com.server.HGUStudentUnion_server.auth.domain.LoginUser;
import com.server.HGUStudentUnion_server.auth.domain.logins.ManagerLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredManagerLogin;
import com.server.HGUStudentUnion_server.notice.application.NoticeService;
import com.server.HGUStudentUnion_server.notice.domain.Notice;
import com.server.HGUStudentUnion_server.notice.presentation.request.NoticeRequest;
import com.server.HGUStudentUnion_server.notice.presentation.request.NoticeUpdateRequest;
import com.server.HGUStudentUnion_server.shareNotice.application.ShareNoticeService;
import com.server.HGUStudentUnion_server.shareNotice.domain.ShareNotice;
import com.server.HGUStudentUnion_server.shareNotice.presentation.request.ShareNoticeRequest;
import com.server.HGUStudentUnion_server.shareNotice.presentation.request.ShareNoticeUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ShareNoticeController {

    @Autowired
    private ShareNoticeService shareNoticeService;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AttachFileService attachFileService;

    @GetMapping("/shareNotices")
    @RequiredLogin
    public ResponseEntity<List<ShareNotice>> findAll (){
        List<ShareNotice> res = shareNoticeService.findAll();
        return ResponseEntity.ok(res);
    }
    @GetMapping("/shareNotices/{shareNoticeId}")
    @RequiredLogin
    public ResponseEntity<ShareNotice> findById (@PathVariable Long shareNoticeId){
        ShareNotice res = shareNoticeService.find(shareNoticeId);
        return ResponseEntity.ok(res);
    }
    @PostMapping("/shareNotices")
    @RequiredManagerLogin
    public ResponseEntity<ShareNotice> save (@ManagerLogin LoginUser loginUser, @RequestBody ShareNoticeRequest request){
        AppUser user = appUserService.findById(loginUser.getId());
        ShareNotice res = shareNoticeService.save(request, user);
        return ResponseEntity.ok(res);
    }
    @PatchMapping("/shareNotices/{shareNoticeId}")
    @RequiredManagerLogin
    public ResponseEntity<ShareNotice> update (@PathVariable Long shareNoticeId, @RequestBody ShareNoticeUpdateRequest request){
        ShareNotice res = shareNoticeService.findById(shareNoticeId);
        if(request.getDelFiles().size()>0){
            attachFileService.deleteById(request.getDelFiles());
        }
        List<AttachFile> newFiles = new ArrayList<>();
        if(request.getUrls().size()>0) {
            newFiles = attachFileService.saveMultiShareFile(request.getUrls(), request.getTitles(), res);
        }
        return ResponseEntity.ok(shareNoticeService.update(shareNoticeId, request, newFiles));
    }
    @DeleteMapping("/shareNotices/{shareNoticeId}")
    @RequiredManagerLogin
    public ResponseEntity<Long> delete (@PathVariable Long shareNoticeId){
        shareNoticeService.delete(shareNoticeId);
        return ResponseEntity.ok(shareNoticeId);
    }
}
