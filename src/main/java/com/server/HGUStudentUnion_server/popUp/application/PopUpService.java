package com.server.HGUStudentUnion_server.popUp.application;

import com.server.HGUStudentUnion_server.banner.domain.PopUp;
import com.server.HGUStudentUnion_server.exception.popUp.PopUpNotFoundException;
import com.server.HGUStudentUnion_server.popUp.domain.repository.PopUpRepo;
import com.server.HGUStudentUnion_server.popUp.presentation.request.PopUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PopUpService {

    @Autowired
    private PopUpRepo popUpRepo;

    @Transactional
    public PopUp findById(Long id){
        return popUpRepo.findById(id).orElseThrow(PopUpNotFoundException::new);
    }

    @Transactional
    public List<PopUp> findAll(){
        return popUpRepo.findAll();
    }

    @Transactional
    public PopUp update(Long id, PopUpRequest request){
        PopUp ret = this.findById(id);
        ret.update(request);
        return ret;
    }

    @Transactional
    public void delete(Long id){
        popUpRepo.deleteById(id);
    }


    @Transactional
    public PopUp save(PopUpRequest request) {
        return popUpRepo.save(PopUp.from(request));
    }
}
