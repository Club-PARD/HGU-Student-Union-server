package com.server.HGUStudentUnion_server.inquiry.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.common.BaseEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;
    private Boolean hide;
    private Boolean access;
    private String title;
    private String content;
    private int status;
    private int viewCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser ansUser;
    private String answer;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime ansTime;

    public void increaseViewCnt(){this.viewCnt++;}

}
