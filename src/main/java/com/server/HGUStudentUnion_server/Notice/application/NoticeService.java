package com.server.HGUStudentUnion_server.Notice.application;

import com.server.HGUStudentUnion_server.AppUser.domain.AppUser;
import com.server.HGUStudentUnion_server.AppUser.domain.repository.AppUserRepo;
import com.server.HGUStudentUnion_server.AttachFile.application.AttachFileService;
import com.server.HGUStudentUnion_server.AttachFile.domain.AttachFile;
import com.server.HGUStudentUnion_server.AttachFile.domain.repository.AttachFileRepo;
import com.server.HGUStudentUnion_server.Notice.domain.Notice;
import com.server.HGUStudentUnion_server.Notice.domain.repository.NoticeRepo;
import com.server.HGUStudentUnion_server.Notice.presentation.request.NoticeRequest;
import com.server.HGUStudentUnion_server.Notice.presentation.request.NoticeUpdateRequest;
import com.server.HGUStudentUnion_server.exception.AppUser.AppUserNotFoundException;
import com.server.HGUStudentUnion_server.exception.notice.NoticeNotFoundException;
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
    private AttachFileRepo attachFileRepo;



    public Notice findById(Long noticeId){
        return noticeRepo.findById(noticeId).orElseThrow(NoticeNotFoundException::new);
    }


    public List<Notice> findAll() {
        List<Notice> ret = noticeRepo.findAll();
        return ret;
    }

    public Notice find(Long noticeId) {
        Notice ret = this.findById(noticeId);
        return ret;
    }

    @Transactional
    public Notice save(NoticeRequest request) {
        AppUser user = appUserRepo.findById(request.getUserId()).orElseThrow(AppUserNotFoundException::new);
        List<AttachFile> files = new ArrayList<>();
        if(request.getUrls().size()>0){
            for(int i=0; i<request.getUrls().size(); i++){
                files.add(AttachFile.builder().title(request.getTitles().get(i)).link(request.getUrls().get(i)).build());
            }
        }

        Notice ret = noticeRepo.save(Notice.from(user, files, request));
        return ret;
    }

    @Transactional
    public Notice update(Long noticeId, NoticeUpdateRequest request) {
        Notice ret = this.findById(noticeId);
        if(request.getDelFiles().size()>0){
            for(int i = 0 ; i< request.getDelFiles().size(); i++){
                attachFileRepo.deleteById(request.getDelFiles().get(i));
            }
        }
        if(request.getUrls().size()>0) {
            for (int i = 0; i < request.getUrls().size(); i++) {
                ret.getFiles().add(AttachFile.builder().title(request.getTitles().get(i)).link(request.getUrls().get(i)).build());
            }
        }
        ret.update(request);
        return ret;
    }

    public void delete(Long noticeId) {
        noticeRepo.deleteById(noticeId);
    }
}
