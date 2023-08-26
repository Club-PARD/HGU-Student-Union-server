package com.server.HGUStudentUnion_server.event.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.HGUStudentUnion_server.common.BaseEntity;
import com.server.HGUStudentUnion_server.event.presentation.request.EventRequest;
import com.server.HGUStudentUnion_server.event.presentation.request.EventUpdateRequest;
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
@SQLDelete(sql = "UPDATE Event SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Event extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean hide;
    private String title;
    private String category;
    @Column(length = 501)
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime end;
    private String link;

    public static Event from(EventRequest request) {
        return Event.builder()
                .hide(false)
                .category(request.getCategory())
                .content(request.getContent())
                .title(request.getTitle())
                .start(request.getStart())
                .end(request.getEnd())
                .link(request.getLink())
                .build();
    }

    public void update(EventUpdateRequest request) {
        this.hide = request.getHide();
        this.title = request.getTitle();
        this.start = request.getStart();
        this.end = request.getEnd();
        this.content = request.getContent();
        this.category = request. getCategory();
        this.link = request.getLink();
    }
}
