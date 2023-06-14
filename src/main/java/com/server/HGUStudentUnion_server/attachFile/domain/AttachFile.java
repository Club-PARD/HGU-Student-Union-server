package com.server.HGUStudentUnion_server.attachFile.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.HGUStudentUnion_server.shareNotice.domain.ShareNotice;
import com.server.HGUStudentUnion_server.notice.domain.Notice;
import com.server.HGUStudentUnion_server.common.BaseEntity;
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
@SQLDelete(sql = "UPDATE AttachFile SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class AttachFile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="notice_id")
    @JsonIgnore
    private Notice notice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="share_notice_id")
    @JsonIgnore
    private ShareNotice shareNotice;


    private String title;
    private String link;

    public static AttachFile noticeFile (String title, String link, Notice notice){
        return AttachFile.builder()
                .title(title)
                .link(link)
                .notice(notice)
                .shareNotice(null)
                .build();
    }


    public static AttachFile shareFile(String title, String link, ShareNotice shareNotice) {
        return AttachFile.builder()
                .title(title)
                .link(link)
                .notice(null)
                .shareNotice(shareNotice)
                .build();
    }
}
