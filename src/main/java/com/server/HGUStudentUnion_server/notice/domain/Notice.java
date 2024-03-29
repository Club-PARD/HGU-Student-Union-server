package com.server.HGUStudentUnion_server.notice.domain;

import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.attachFile.domain.AttachFile;
import com.server.HGUStudentUnion_server.notice.presentation.request.NoticeRequest;
import com.server.HGUStudentUnion_server.notice.presentation.request.NoticeUpdateRequest;
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
@SQLDelete(sql = "UPDATE Notice SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Notice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "APPUSER_ID")
    private AppUser user;


    private Boolean pin;
    private Boolean hide;
    private String title;
    private String image;

    @Column(length = 65555)
    private String content;
    private int viewCnt;

    public void increaseViewCnt(){this.viewCnt++;}

    public static Notice from (AppUser user, NoticeRequest request) {
        return Notice.builder()
                .user(user)
                .pin(request.getPin())
                .hide(false)
                .title(request.getTitle())
                .image(request.getImage())
                .content(request.getContent())
                .build();
    }



}
