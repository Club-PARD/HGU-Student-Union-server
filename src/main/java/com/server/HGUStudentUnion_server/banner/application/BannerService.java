package com.server.HGUStudentUnion_server.banner.application;

import com.server.HGUStudentUnion_server.banner.domain.Banner;
import com.server.HGUStudentUnion_server.banner.domain.repository.BannerRepo;
import com.server.HGUStudentUnion_server.banner.presentation.request.BannerRequest;
import com.server.HGUStudentUnion_server.exception.banner.BannerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BannerService {
    @Autowired
    private BannerRepo bannerRepo;

    @Transactional
    public Banner findById(Long id){
        return bannerRepo.findById(id).orElseThrow(BannerNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Banner> findAll(){
        return bannerRepo.findAll();
    }
    @Transactional
    public Banner save(BannerRequest request){
        return bannerRepo.save(Banner.from(request));
    }

    @Transactional
    public Banner update(Long id, BannerRequest request){
        Banner ret = this.findById(id);
        ret.update(request);
        return ret;
    }

    @Transactional
    public void delete(Long id){
        bannerRepo.deleteById(id);
    }


}
