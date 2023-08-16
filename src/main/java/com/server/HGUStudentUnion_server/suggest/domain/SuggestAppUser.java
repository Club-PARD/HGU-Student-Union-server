package com.server.HGUStudentUnion_server.suggest.domain;

import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE SuggestAppUser SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class SuggestAppUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "APPUSER_ID")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "SUGGEST_ID")
    private Suggest suggest;

    public static SuggestAppUser from(AppUser recommendUser, Suggest ret) {
        return SuggestAppUser.builder()
                .appUser(recommendUser)
                .suggest(ret)
                .build();
    }
}
