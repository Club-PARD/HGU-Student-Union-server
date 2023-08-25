package com.server.HGUStudentUnion_server.shareNotice.domain;

import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.attachFile.domain.AttachFile;
import com.server.HGUStudentUnion_server.common.BaseEntity;
import com.server.HGUStudentUnion_server.notice.presentation.request.NoticeUpdateRequest;
import com.server.HGUStudentUnion_server.shareNotice.presentation.request.ShareNoticeRequest;
import com.server.HGUStudentUnion_server.shareNotice.presentation.request.ShareNoticeUpdateRequest;
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
@SQLDelete(sql = "UPDATE ShareNotice SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class ShareNotice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppUser user;

    @OneToMany(fetch = FetchType.EAGER)
    private List<AttachFile> files = new ArrayList<>();

    private int category;
    private Boolean hide;
    private Boolean pin;
    private String title;
    @Column(length = 5555)
    private String content;
    private int viewCnt;

    public void increaseViewCnt(){this.viewCnt++;}

    public static ShareNotice from (ShareNoticeRequest request, AppUser user){
        return ShareNotice.builder()
                .user(user)
                .pin(request.getPin())
                .category(request.getCategory())
                .files(null)
                .hide(false)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }

    public void addFiles(List<AttachFile> files){
        this.files = files;
    }

    public void update(ShareNoticeUpdateRequest request, List<AttachFile> newFiles) {
        this.category = request.getCategory();
        this.hide = request.getHide();
        this.pin = request.getPin();
        this.title = request.getTitle();
        this.content = request.getContent();
        for(int i =0; i< newFiles.size(); i++){
            this.files.add(newFiles.get(i));
        }
    }
}
