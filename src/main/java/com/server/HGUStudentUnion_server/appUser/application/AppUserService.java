package com.server.HGUStudentUnion_server.appUser.application;

import com.server.HGUStudentUnion_server.appUser.domain.Admin;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.appUser.domain.repository.AdminRepo;
import com.server.HGUStudentUnion_server.appUser.domain.repository.AppUserRepo;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AdminRequest;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AppUserRequest;
import com.server.HGUStudentUnion_server.exception.AppUser.AppUserNotFoundException;
import com.server.HGUStudentUnion_server.suggest.domain.Suggest;
import com.server.HGUStudentUnion_server.suggest.presentation.request.RecommendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private AdminRepo adminRepo;


    @Transactional
    public AppUser findById(Long userId) {
        return appUserRepo.findById(userId).orElseThrow(AppUserNotFoundException::new);
    }

    @Transactional
    public AppUser find(Long userId){
        return this.findById(userId);
    }

    @Transactional(readOnly = true)
    public List<AppUser> findAll() {
        return appUserRepo.findAll();
    }

    @Transactional
    public AppUser save(AppUserRequest request) {
        AppUser ret = appUserRepo.save(AppUser.from(request));
        return ret;
    }


    @Transactional
    public void delete(Long appUserId) {
        AppUser del = this.findById(appUserId);
        appUserRepo.delete(del);
    }

    @Transactional
    public AppUser update(Long appUserId, AppUserRequest request) {
        AppUser ret = this.findById(appUserId);
        ret.update(request);
        return ret;
    }

    @Transactional
    public Admin newAdmin() {
        return adminRepo.save(Admin.builder().build());
    }

    @Transactional
    public void deleteAdmin(Long id) {
        adminRepo.deleteById(id);
    }

    @Transactional
    public AppUser updateAdminProfile(Long appUserId, AdminRequest request) {
        AppUser ret = this.findById(appUserId);
        ret.updateAdmin(request);
        return ret;
    }
}
