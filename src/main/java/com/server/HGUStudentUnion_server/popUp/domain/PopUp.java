package com.server.HGUStudentUnion_server.banner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.HGUStudentUnion_server.common.BaseEntity;
import com.server.HGUStudentUnion_server.popUp.presentation.request.PopUpRequest;
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
@SQLDelete(sql = "UPDATE PopUp SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class PopUp extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int status; // 0 미게시 1 게시 2 대기

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime end;

    private Boolean targetDevice; // 0 모바일 1 데탑
    private Boolean targetVisitor; // 0 로그인 1 All

    private int locationVertical;
    private int locationHorizontal;
    private String image;
    private String link;

    public static PopUp from(PopUpRequest request) {
        return PopUp.builder()
                .title(request.getTitle())
                .status(request.getStatus())
                .start(request.getStart())
                .end(request.getEnd())
                .targetDevice(request.getTargetDevice())
                .targetVisitor(request.getTargetVisitor())
                .locationHorizontal(request.getLocationHorizontal())
                .locationVertical(request.getLocationVertical())
                .image(request.getImage())
                .link(request.getLink())
                .build();
    }

    public void update(PopUpRequest request) {
        this.title = request.getTitle();
        this.status = request.getStatus();
        this.start = request.getStart();
        this.end = request.getEnd();
        this.targetDevice = request.getTargetDevice();
        this.targetVisitor = request.getTargetVisitor();
        this.locationVertical = request.getLocationVertical();
        this.locationHorizontal = request.getLocationHorizontal();
        this.image = request.getImage();
        this.link = request.getLink();
    }
}
