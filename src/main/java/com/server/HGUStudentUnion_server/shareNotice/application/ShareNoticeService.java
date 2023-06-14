package com.server.HGUStudentUnion_server.shareNotice.application;

import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.attachFile.application.AttachFileService;
import com.server.HGUStudentUnion_server.attachFile.domain.AttachFile;
import com.server.HGUStudentUnion_server.attachFile.domain.repository.AttachFileRepo;
import com.server.HGUStudentUnion_server.exception.shareNotice.ShareNoticeNotFoundException;
import com.server.HGUStudentUnion_server.notice.domain.Notice;
import com.server.HGUStudentUnion_server.notice.presentation.request.NoticeRequest;
import com.server.HGUStudentUnion_server.notice.presentation.request.NoticeUpdateRequest;
import com.server.HGUStudentUnion_server.shareNotice.domain.ShareNotice;
import com.server.HGUStudentUnion_server.shareNotice.domain.repository.ShareNoticeRepo;
import com.server.HGUStudentUnion_server.shareNotice.presentation.request.ShareNoticeRequest;
import com.server.HGUStudentUnion_server.shareNotice.presentation.request.ShareNoticeUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShareNoticeService {

    @Autowired
    private ShareNoticeRepo shareNoticeRepo;

    @Autowired
    private AttachFileService attachFileService;

    @Transactional
    public ShareNotice findById(Long shareNoticeId) {
        return shareNoticeRepo.findById(shareNoticeId).orElseThrow(ShareNoticeNotFoundException::new);
    }
    @Transactional(readOnly = true)
    public List<ShareNotice> findAll() {
        return shareNoticeRepo.findAll();
    }
    @Transactional
    public ShareNotice find(Long shareNoticeId) {
        ShareNotice ret = this.findById(shareNoticeId);
        ret.increaseViewCnt();
        return ret;
    }

    @Transactional
    public ShareNotice save(ShareNoticeRequest request, AppUser user) {
        ShareNotice ret = shareNoticeRepo.save(ShareNotice.from(request, user));
        List<AttachFile> files = new ArrayList<>();
        if(request.getUrls().size()>0){
            files = attachFileService.saveMultiShareFile(request.getUrls(),request.getTitles(), ret);
        }
        ret.addFiles(files);
        return ret;
    }

    @Transactional
    public ShareNotice update(Long shareNoticeId, ShareNoticeUpdateRequest request, List<AttachFile> newFiles) {
        ShareNotice ret = this.findById(shareNoticeId);
        ret.update(request, newFiles);
        return ret;
    }

    @Transactional
    public void delete(Long shareNoticeId) {
        shareNoticeRepo.deleteById(shareNoticeId);
    }


}
