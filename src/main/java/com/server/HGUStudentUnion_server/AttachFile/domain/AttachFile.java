package com.server.HGUStudentUnion_server.AttachFile.domain;

import com.server.HGUStudentUnion_server.ShareNotice.domain.ShareNotice;
import com.server.HGUStudentUnion_server.Notice.domain.Notice;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="notice_id")
    private Notice notice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="share_notice_id")
    private ShareNotice shareNotice;


    private String title;
    private String link;
}
