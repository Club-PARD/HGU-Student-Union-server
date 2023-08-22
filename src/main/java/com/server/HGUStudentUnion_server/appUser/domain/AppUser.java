package com.server.HGUStudentUnion_server.appUser.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AdminRequest;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AppUserRequest;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AuthRequest;
import com.server.HGUStudentUnion_server.suggest.domain.SuggestAppUser;
import com.server.HGUStudentUnion_server.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE AppUser SET deleted = true WHERE APPUSER_ID = ?")
@Where(clause = "deleted = false")
public class AppUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="APPUSER_ID")
    private Long id;
    private int auth; // 1: 일반회원 2: 일반관리자 3: 총학관리자 4: 마스터관리자
    private String name;
    private String email;

    @OneToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;


    public static AppUser from(AppUserRequest request) {
        return AppUser.builder()
                .auth(request.getAuth())
                .name(request.getName())
                .email(request.getEmail())
                .admin(null)
                .build();
    }


    public void updateProfile(AdminRequest request){
        this.name = request.getName();
    }
    public void setAdmin(Admin admin){
        this.admin = admin;
    }
    public void updateAuth(AuthRequest request) {
        this.auth = request.getAuth();
    }

    public boolean isSuperManager() {
        return (this.auth == 4);
    }

    public boolean isSUManager() {
        return (this.auth >= 3);
    }
    public boolean isManager() {
        return (this.auth >= 2);
    }
    public boolean isNormal() {
        return (this.auth >= 1);
    }


}
