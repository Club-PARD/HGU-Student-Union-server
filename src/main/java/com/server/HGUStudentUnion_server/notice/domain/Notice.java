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

    @OneToMany(fetch = FetchType.EAGER)
    private List<AttachFile> files = new ArrayList<>();

    private Boolean pin;
    private Boolean hide;
    private String title;
    private String image;
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
    public void addFiles(List<AttachFile> files){this.files = files;}


    public void update (NoticeUpdateRequest request, List<AttachFile> newFiles){
        this.title = request.getTitle();
        this.image = request.getImage();
        this.hide = request.getHide();
        this.content = request.getContent();
        this.pin = request.getPin();
        for(int i=0; i< newFiles.size(); i++){
            this.getFiles().add(newFiles.get(i));
        }
    }


}
