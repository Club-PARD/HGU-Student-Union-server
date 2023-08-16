package com.server.HGUStudentUnion_server.popUp.presentation.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PopUpRequest {
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
}
