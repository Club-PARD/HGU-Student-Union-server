package com.server.HGUStudentUnion_server.appUser.presentation.response;

import com.server.HGUStudentUnion_server.appUser.domain.Admin;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.GeneratedValue;

@Getter
@AllArgsConstructor
public class AppUserProfile {
    private String name;
    private String email;
    private Admin admin;

    public static AppUserProfile of(AppUser appUser){
        return new AppUserProfile(
                appUser.getName(), appUser.getEmail(), appUser.getAdmin()
        );
    }
}
