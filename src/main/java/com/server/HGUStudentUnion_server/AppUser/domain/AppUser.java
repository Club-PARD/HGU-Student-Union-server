package com.server.HGUStudentUnion_server.AppUser.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.HGUStudentUnion_server.Suggest.domain.SuggestAppUser;
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
@SQLDelete(sql = "UPDATE AppUser SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class AppUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="APPUSER_ID")
    private Long id;

    private int auth;
    private String name;
    private String email;



    @OneToMany(mappedBy = "appUser")
    @JsonIgnore
    private List<SuggestAppUser> recommendList = new ArrayList<>();


}
