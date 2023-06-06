package com.server.HGUStudentUnion_server.Suggest.domain;

import com.server.HGUStudentUnion_server.AppUser.domain.AppUser;

import javax.persistence.*;

@Entity
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
