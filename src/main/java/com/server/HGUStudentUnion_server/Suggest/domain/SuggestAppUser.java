package com.server.HGUStudentUnion_server.Suggest.domain;

import com.server.HGUStudentUnion_server.AppUser.domain.AppUser;
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
@SQLDelete(sql = "UPDATE AppUser SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class SuggestAppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "APPUSER_ID")
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "SUGGEST_ID")
    private Suggest suggest;

}
