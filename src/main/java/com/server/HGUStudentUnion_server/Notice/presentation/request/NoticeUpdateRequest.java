package com.server.HGUStudentUnion_server.Notice.presentation.request;

import com.server.HGUStudentUnion_server.AttachFile.domain.AttachFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeUpdateRequest {
    private List<String> titles;
    private List<String> urls;
    private List<Long> delFiles;
    private String title;
    private String image;
    private String content;
    private Boolean pin;
}
