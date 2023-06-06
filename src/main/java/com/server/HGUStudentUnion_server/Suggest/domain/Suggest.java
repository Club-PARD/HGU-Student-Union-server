package com.server.HGUStudentUnion_server.Suggest.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.HGUStudentUnion_server.AppUser.domain.AppUser;
import com.server.HGUStudentUnion_server.common.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Suggest extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;

    @OneToMany(mappedBy = "suggest")
    @JsonIgnore
    private List<SuggestAppUser> recommendList = new ArrayList<>();

    private Boolean hide;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
    private int status;
    private int viewCnt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser ansUser;

    @Column(columnDefinition = "TEXT")
    private String answer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime ansTime;


    public void increaseViewCnt(){this.viewCnt++;}



}
