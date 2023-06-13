package com.server.HGUStudentUnion_server.ShareNotice.domain;

import com.server.HGUStudentUnion_server.AppUser.domain.AppUser;
import com.server.HGUStudentUnion_server.AttachFile.domain.AttachFile;
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
@SQLDelete(sql = "UPDATE ShareNotice SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class ShareNotice extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;

    @OneToMany(fetch = FetchType.LAZY)
    private List<AttachFile> files = new ArrayList<>();

    private int category;
    private Boolean hide;
    private String title;
    private String content;
    private int viewCnt;
}
