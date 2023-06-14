package com.server.HGUStudentUnion_server.attachFile.application;

import com.server.HGUStudentUnion_server.attachFile.domain.AttachFile;
import com.server.HGUStudentUnion_server.attachFile.domain.repository.AttachFileRepo;
import com.server.HGUStudentUnion_server.notice.domain.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttachFileService {

    @Autowired
    private AttachFileRepo attachFileRepo;

    @Transactional
    public AttachFile saveNoticeFile (String title, String link, Notice notice){
        AttachFile ret = attachFileRepo.save(AttachFile.noticeFile(title,link, notice));
        return ret;
    }

    @Transactional
    public void deleteById(List<Long> attachFileIds) {
        attachFileRepo.deleteAllById(attachFileIds);
    }

    @Transactional
    public List<AttachFile> saveMultiNoticeFile(List<String> urls, List<String> titles, Notice notice) {
        List<AttachFile> ret = new ArrayList<>();
        for (int i = 0; i < urls.size(); i++) {
                ret.add( attachFileRepo.save(AttachFile.noticeFile(titles.get(i), urls.get(i), notice)));
        }

        return ret;
    }
}
