package com.server.HGUStudentUnion_server.appUser.application;

import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.appUser.domain.repository.AppUserRepo;
import com.server.HGUStudentUnion_server.exception.AppUser.AppUserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepo appUserRepo;


    @Transactional
    public AppUser findById(Long userId) {
        return appUserRepo.findById(userId).orElseThrow(AppUserNotFoundException::new);
    }

    @Transactional
    public AppUser find(Long userId){
        return this.findById(userId);
    }
}
