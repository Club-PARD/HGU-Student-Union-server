package com.server.HGUStudentUnion_server.suggest.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.common.BaseEntity;
import com.server.HGUStudentUnion_server.suggest.presentation.request.SuggestAnswerRequest;
import com.server.HGUStudentUnion_server.suggest.presentation.request.SuggestRequest;
import com.server.HGUStudentUnion_server.suggest.presentation.request.SuggestUpdateRequest;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE Suggest SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Suggest extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "APPUSER_ID", name="writerId", nullable = false)
    private AppUser appUser;

    private Boolean hide;
    private String title;

    private String content;
    private int status;
    private int viewCnt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "APPUSER_ID", name="ansUserId")
    private AppUser ansUser;

    private String answer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime ansTime;

    public static Suggest from(AppUser writer, SuggestRequest request) {
        return Suggest.builder()
                .appUser(writer)
                .hide(false)
                .title(request.getTitle())
                .content(request.getContent())
                .status(0)
                .viewCnt(0)
                .ansUser(null)
                .answer(null)
                .ansTime(null)
                .build();
    }


    public void increaseViewCnt(){this.viewCnt++;}


    public void update(SuggestUpdateRequest request) {
        this.hide = request.getHide();
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    public void insertAnswer(AppUser ansUser, SuggestAnswerRequest request) {
        this.ansUser = ansUser;
        this.answer = request.getAnswer();
        this.ansTime = LocalDateTime.now();
        this.status = 2;
    }
    public void updateStatus(int status){
        this.status = status;
    }

}
