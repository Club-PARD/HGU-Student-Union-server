package com.server.HGUStudentUnion_server.attachFile.presentation.response;

import com.server.HGUStudentUnion_server.attachFile.domain.AttachFile;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AttachFileResponse {
    private String title;
    private String link;

    public static AttachFileResponse of(AttachFile attachFile){
        return new AttachFileResponse(
                attachFile.getTitle(),
                attachFile.getLink());
    }
}
