package com.server.HGUStudentUnion_server.notice.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeRequest {
    private List<String> titles;
    private List<String> urls;
    private String title;
    private String image;
    private String content;
    private Boolean pin;
}
