package com.server.HGUStudentUnion_server.inquiry.application;

import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.exception.Inquiry.InquiryNotFoundException;
import com.server.HGUStudentUnion_server.inquiry.domain.Inquiry;
import com.server.HGUStudentUnion_server.inquiry.domain.repository.InquiryRepo;
import com.server.HGUStudentUnion_server.inquiry.presentation.request.AnswerRequest;
import com.server.HGUStudentUnion_server.inquiry.presentation.request.InquiryRequest;
import com.server.HGUStudentUnion_server.inquiry.presentation.request.InquiryUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InquiryService {

    @Autowired
    private InquiryRepo inquiryRepo;

    private Inquiry findById(Long id){
        return inquiryRepo.findById(id).orElseThrow(InquiryNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Inquiry> findAll() {
        return inquiryRepo.findAll();
    }

    @Transactional
    public Inquiry find(Long id) {
        Inquiry ret = this.findById(id);
        ret.increaseViewCnt();
        return ret;
    }

    @Transactional
    public Inquiry save(AppUser writer, InquiryRequest request) {
        return inquiryRepo.save(Inquiry.from(writer, request));
    }

    @Transactional
    public Inquiry update(Long id, InquiryUpdateRequest request) {
        Inquiry ret = this.findById(id);
        ret.update(request);
        return ret;
    }

    @Transactional
    public void delete(Long id) {
        inquiryRepo.deleteById(id);
    }

    @Transactional
    public Inquiry updateStatus(Long id, int status) {
        Inquiry ret = this.findById(id);
        ret.updateStatus(status);
        return ret;
    }

    @Transactional
    public Inquiry answer(Long id, AppUser ansUser, AnswerRequest request) {
        Inquiry ret = this.findById(id);
        ret.registerAnswer(request, ansUser);
        return ret;
    }
}
