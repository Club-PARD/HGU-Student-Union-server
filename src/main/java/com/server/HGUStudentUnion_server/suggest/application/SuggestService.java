package com.server.HGUStudentUnion_server.suggest.application;

import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.appUser.domain.repository.AppUserRepo;
import com.server.HGUStudentUnion_server.exception.AppUser.AppUserNotFoundException;
import com.server.HGUStudentUnion_server.exception.suggest.SuggestNotFoundException;
import com.server.HGUStudentUnion_server.suggest.domain.Suggest;
import com.server.HGUStudentUnion_server.suggest.domain.SuggestAppUser;
import com.server.HGUStudentUnion_server.suggest.domain.repository.SuggestAppUserRepo;
import com.server.HGUStudentUnion_server.suggest.domain.repository.SuggestRepo;
import com.server.HGUStudentUnion_server.suggest.presentation.request.*;
import com.server.HGUStudentUnion_server.suggest.presentation.response.SuggestDetailResponse;
import com.server.HGUStudentUnion_server.suggest.presentation.response.SuggestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SuggestService {

    @Autowired
    private SuggestRepo suggestRepo;

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private SuggestAppUserRepo suggestAppUserRepo;


    private Suggest findById(Long id){
        return suggestRepo.findById(id).orElseThrow(SuggestNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<SuggestResponse> findAll() {
        List<SuggestResponse> res = new ArrayList<>();
        List<Suggest> temp = suggestRepo.findAll();
        for(int i=0; i<temp.size(); i++){
            List<SuggestAppUser> t = suggestAppUserRepo.findBySId(temp.get(i).getId());
            res.add(SuggestResponse.of(temp.get(i), t.size()));
        }
        return res;
    }

    @Transactional
    public SuggestDetailResponse find(Long id, Long userId) {
        Suggest sug = this.findById(id);
        sug.increaseViewCnt();
        Optional<SuggestAppUser> suggestAppUser = suggestAppUserRepo.findByUserIdAndSuggestId(userId, id);
        List<SuggestAppUser> suggestAppUsers = suggestAppUserRepo.findBySId(id);
        if(suggestAppUsers != null){
            return SuggestDetailResponse.of(sug, suggestAppUser.isPresent(), suggestAppUsers.size());
        }
        else return SuggestDetailResponse.of(sug, Boolean.FALSE, 0);

    }

    @Transactional
    public Suggest save(Long writerId, SuggestRequest request) {
        AppUser writer = appUserRepo.findById(writerId).orElseThrow(AppUserNotFoundException::new);
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
    public Long recommend(AppUser recommendUser, RecommendRequest request) {
        Suggest suggest = this.findById(request.getSuggestId());
        SuggestAppUser ret = suggestAppUserRepo.save(SuggestAppUser.from(recommendUser, suggest));
        return ret.getId();
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
