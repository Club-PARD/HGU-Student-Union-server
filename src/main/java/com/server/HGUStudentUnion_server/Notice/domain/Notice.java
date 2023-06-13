package com.server.HGUStudentUnion_server.Notice.domain;

import com.server.HGUStudentUnion_server.AppUser.domain.AppUser;
import com.server.HGUStudentUnion_server.AttachFile.domain.AttachFile;
import com.server.HGUStudentUnion_server.Notice.presentation.request.NoticeRequest;
import com.server.HGUStudentUnion_server.Notice.presentation.request.NoticeUpdateRequest;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;

    @OneToMany(fetch = FetchType.LAZY)
    private List<AttachFile> files = new ArrayList<>();

    private Boolean pin;
    private Boolean hide;
    private String title;
    private String image;
    private String content;
    private int viewCnt;

    public void increaseViewCnt(){this.viewCnt++;}

    public static Notice from (AppUser user, List<AttachFile> files, NoticeRequest request){
        return Notice.builder()
                .user(user)
                .files(files)
                .pin(request.getPin())
                .hide(false)
                .title(request.getTitle())
                .image(request.getImage())
                .content(request.getContent())
                .build();
    }


    public void update (NoticeUpdateRequest request){
        this.title = request.getTitle();
        this.image = request.getImage();
        this.content = request.getContent();
        this.pin = request.getPin();
    }


}
