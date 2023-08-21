package com.server.HGUStudentUnion_server.banner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.server.HGUStudentUnion_server.banner.presentation.request.BannerRequest;
import com.server.HGUStudentUnion_server.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@SQLDelete(sql = "UPDATE Banner SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Banner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Boolean hide;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime end;
    private String webImage;
    private String mobileImage;

    public static Banner from(BannerRequest request){
        return Banner.builder()
                .title(request.getTitle())
                .hide(request.getHide())
                .start(request.getStart())
                .end(request.getEnd())
                .webImage(request.getWebImage())
                .mobileImage(request.getMobileImage())
                .build();
    }

    public void update(BannerRequest request) {
        this.title = request.getTitle();
        this.hide = request.getHide();
        this.start = request.getStart();
        this.end = request.getEnd();
        this.webImage = request.getWebImage();
        this.mobileImage = request.getMobileImage();
    }
}
