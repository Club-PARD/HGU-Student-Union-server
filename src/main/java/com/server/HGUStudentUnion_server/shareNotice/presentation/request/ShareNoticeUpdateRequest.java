package com.server.HGUStudentUnion_server.shareNotice.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShareNoticeUpdateRequest {
    private List<String> titles;
    private List<String> urls;
    private List<Long> delFiles;
    private Boolean pin;
    private int category;
    private String title;
    private String content;
    private Boolean hide;
}
