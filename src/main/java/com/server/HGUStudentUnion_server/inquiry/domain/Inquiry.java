package com.server.HGUStudentUnion_server.inquiry.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.common.BaseEntity;
import com.server.HGUStudentUnion_server.inquiry.presentation.request.AnswerRequest;
import com.server.HGUStudentUnion_server.inquiry.presentation.request.InquiryRequest;
import com.server.HGUStudentUnion_server.inquiry.presentation.request.InquiryUpdateRequest;
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
@SQLDelete(sql = "UPDATE Inquiry SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Inquiry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "APPUSER_ID", name="writerId", nullable = false)
    private AppUser user;

    private Boolean hide;
    private Boolean access;
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

    public void increaseViewCnt(){this.viewCnt= this.viewCnt+1;}

    public static Inquiry from (AppUser user, InquiryRequest request){
        return Inquiry.builder()
                .user(user)
                .hide(false)
                .access(request.getAccess())
                .title(request.getTitle())
                .content(request.getContent())
                .status(0)
                .viewCnt(0)
                .ansUser(null)
                .answer(null)
                .ansTime(null)
                .build();
    }
    public void update(InquiryUpdateRequest request){
        this.hide = request.getHide();
        this.access = request.getAccess();
        this.title = request.getTitle();
        this.content = request.getContent();
    }
    public void registerAnswer(AnswerRequest request, AppUser ansUser){
        this.ansUser = ansUser;
        this.answer = request.getAnswer();
        this.ansTime = LocalDateTime.now();
    }

    public void updateStatus(int status) {
        this.status = status;
    }
}
