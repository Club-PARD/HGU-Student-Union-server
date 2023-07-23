package com.server.HGUStudentUnion_server.suggest.application;

import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.exception.suggest.SuggestNotFoundException;
import com.server.HGUStudentUnion_server.suggest.domain.Suggest;
import com.server.HGUStudentUnion_server.suggest.domain.SuggestAppUser;
import com.server.HGUStudentUnion_server.suggest.domain.repository.SuggestAppUserRepo;
import com.server.HGUStudentUnion_server.suggest.domain.repository.SuggestRepo;
import com.server.HGUStudentUnion_server.suggest.presentation.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SuggestService {

    @Autowired
    private SuggestRepo suggestRepo;

    @Autowired
    private SuggestAppUserRepo suggestAppUserRepo;


    private Suggest findById(Long id){
        return suggestRepo.findById(id).orElseThrow(SuggestNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Suggest> findAll() {
        return suggestRepo.findAll();
    }
    @Transactional
    public Suggest find(Long id) {
        Suggest ret = this.findById(id);
        ret.increaseViewCnt();
        return ret;
    }

    @Transactional
    public Suggest save(AppUser writer, SuggestRequest request) {
        return suggestRepo.save(Suggest.from(writer, request));
    }

    @Transactional
    public Suggest update(Long suggestId, SuggestUpdateRequest request) {
        Suggest ret = this.findById(suggestId);
        ret.update(request);
        return ret;
    }

    @Transactional
    public void delete(Long id) {
        suggestRepo.deleteById(id);
    }

    @Transactional
    public Suggest status(Long suggestId, SuggestStatusRequest request) {
        Suggest ret = this.findById(suggestId);
        ret.setStatus(request.getStatus());
        return ret;
    }

    @Transactional
    public Suggest answer(Long suggestId, AppUser ansUser, SuggestAnswerRequest request) {
        Suggest ret = this.findById(suggestId);
        ret.insertAnswer(ansUser, request);
        return ret;
    }

    @Transactional
    public SuggestAppUser recommend(AppUser recommendUser, RecommendRequest request) {
        Suggest suggest = this.findById(request.getSuggestId());
        return suggestAppUserRepo.save(SuggestAppUser.from(recommendUser, suggest));
    }

//    @Transactional
//    public SuggestAppUser recommend(AppUser recommendUser, RecommendRequest request) {
//        Suggest suggest = this.findById(request.getSuggestId());
//        SuggestAppUser ret = suggestAppUserRepo.save(SuggestAppUser.from(recommendUser, suggest));
//        recommendUser.insertSuggestAppUser(ret);
//        suggest.insertSuggestAppUser(ret);
//        return ret;
//    }
}
