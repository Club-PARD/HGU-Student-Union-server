package com.server.HGUStudentUnion_server.shop.application;

import com.server.HGUStudentUnion_server.exception.shop.ShopNotFoundException;
import com.server.HGUStudentUnion_server.shop.domain.Shop;
import com.server.HGUStudentUnion_server.shop.domain.repository.ShopRepo;
import com.server.HGUStudentUnion_server.shop.presentation.request.ShopRequest;
import com.server.HGUStudentUnion_server.shop.presentation.request.ShopUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ShopRepo shopRepo;


    public Shop find(Long shopId){
        return shopRepo.findById(shopId).orElseThrow(ShopNotFoundException::new);
    }
    @Transactional(readOnly = true)
    public List<Shop> findAll() {
        List<Shop> ret = shopRepo.findAll();
        return ret;
    }

    @Transactional
    public Shop save(ShopRequest request) {
        Shop ret = shopRepo.save(Shop.from(request));
        return ret;
    }

    @Transactional
    public Shop update(Long shopId, ShopUpdateRequest request) {
        Shop ret = this.find(shopId);
        ret.update(request);
        return ret;
    }
    @Transactional
    public void delete(Long shopId) {
        Shop del = this.find(shopId);
        shopRepo.delete(del);
    }
}
