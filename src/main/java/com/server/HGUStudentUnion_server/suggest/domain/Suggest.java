package com.server.HGUStudentUnion_server.suggest.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
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
public class Suggest extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SUGGEST_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appUser;

    @OneToMany(mappedBy = "suggest")
    @JsonIgnore
    private List<SuggestAppUser> recommendList = new ArrayList<>();

    private Boolean hide;
    private String title;

    private String content;
    private int status;
    private int viewCnt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser ansUser;

    private String answer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime ansTime;


    public void increaseViewCnt(){this.viewCnt++;}



}
