package com.server.HGUStudentUnion_server.notice.application;

import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.appUser.domain.repository.AppUserRepo;
import com.server.HGUStudentUnion_server.attachFile.application.AttachFileService;
import com.server.HGUStudentUnion_server.attachFile.domain.AttachFile;
import com.server.HGUStudentUnion_server.attachFile.domain.repository.AttachFileRepo;
import com.server.HGUStudentUnion_server.auth.domain.LoginUser;
import com.server.HGUStudentUnion_server.notice.domain.Notice;
import com.server.HGUStudentUnion_server.notice.domain.repository.NoticeRepo;
import com.server.HGUStudentUnion_server.notice.presentation.request.NoticeRequest;
import com.server.HGUStudentUnion_server.notice.presentation.request.NoticeUpdateRequest;
import com.server.HGUStudentUnion_server.exception.AppUser.AppUserNotFoundException;
import com.server.HGUStudentUnion_server.exception.notice.NoticeNotFoundException;
import com.server.HGUStudentUnion_server.notice.presentation.response.NoticeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepo noticeRepo;

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private AttachFileService attachFileService;



    public Notice findById(Long noticeId){
        return noticeRepo.findById(noticeId).orElseThrow(NoticeNotFoundException::new);
    }


    @Transactional(readOnly = true)
    public List<Notice> findAll() {
        List<Notice> ret = noticeRepo.findAll();
        return ret;
    }

    @Transactional
    public Notice find(Long noticeId) {
        Notice ret = this.findById(noticeId);
        ret.increaseViewCnt();
        return ret;
    }

    @Transactional
    public Notice save(LoginUser loginUser, NoticeRequest request) {
        AppUser user = appUserRepo.findById(loginUser.getId()).orElseThrow(AppUserNotFoundException::new);
        Notice ret = noticeRepo.save(Notice.from(user, request));
        if(request.getUrls().size()>0){
            for(int i=0; i<request.getUrls().size(); i++){
                attachFileService.saveNoticeFile(request.getTitles().get(i), request.getUrls().get(i), ret);
            }
        }
        return ret;
    }

    @Transactional
    public void delete(Long noticeId) {
        noticeRepo.deleteById(noticeId);
    }

    @Transactional
    public NoticeResponse patchUpdate(Long noticeId, NoticeUpdateRequest request) {
        Notice ret = this.findById(noticeId);
        if(request.getTitle() != null) ret.setTitle(request.getTitle());
        if(request.getHide() != null) ret.setHide(request.getHide());
        if(request.getImage() != null) ret.setImage(request.getImage());
        if(request.getContent() != null) ret.setContent(request.getContent());
        if(request.getPin() != null) ret.setPin(request.getPin());
        if(request.getUrls() != null){
            if(request.getUrls().size()>0) {
                attachFileService.saveMultiNoticeFile(request.getUrls(), request.getTitles(), ret);
            }
        }
        if(request.getDelFiles() != null){
            if(request.getDelFiles().size()>0){
                attachFileService.deleteById(request.getDelFiles());
            }
        }
        List<AttachFile> files = attachFileService.findByNoticeId(ret.getId());
        return NoticeResponse.from(ret, files);
    }
}
