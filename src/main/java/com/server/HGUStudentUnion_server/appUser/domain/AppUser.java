package com.server.HGUStudentUnion_server.appUser.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.HGUStudentUnion_server.appUser.presentation.request.AppUserRequest;
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

    private int auth;
    private String name;
    private String email;



    @OneToMany(mappedBy = "appUser")
    @JsonIgnore
    private List<SuggestAppUser> recommendList = new ArrayList<>();


    public static AppUser from(AppUserRequest request) {
        return AppUser.builder()
                .auth(request.getAuth())
                .name(request.getName())
                .email(request.getEmail())
                .build();
    }

    public void update(AppUserRequest request) {
        this.auth = request.getAuth();
        this.name = request.getName();
        this.email = request.getEmail();
    }

    public void insertSuggestAppUser (SuggestAppUser suggestAppUser){
        this.recommendList.add(suggestAppUser);
    }
}
