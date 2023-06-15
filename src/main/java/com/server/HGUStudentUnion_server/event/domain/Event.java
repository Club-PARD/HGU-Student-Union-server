package com.server.HGUStudentUnion_server.event.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.HGUStudentUnion_server.common.BaseEntity;
import com.server.HGUStudentUnion_server.event.presentation.request.EventRequest;
import com.server.HGUStudentUnion_server.event.presentation.request.EventUpdateRequest;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime end;

    public static Event from(EventRequest request) {
        return Event.builder()
                .hide(false)
                .title(request.getTitle())
                .start(request.getStart())
                .end(request.getEnd())
                .build();
    }

    public void update(EventUpdateRequest request) {
        this.hide = request.getHide();
        this.title = request.getTitle();
        this.start = request.getStart();
        this.end = request.getEnd();
    }
}
