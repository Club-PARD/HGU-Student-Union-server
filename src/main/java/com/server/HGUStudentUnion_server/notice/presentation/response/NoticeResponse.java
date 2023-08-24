package com.server.HGUStudentUnion_server.notice.presentation.response;

import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import com.server.HGUStudentUnion_server.appUser.presentation.response.AppUserProfile;
import com.server.HGUStudentUnion_server.attachFile.domain.AttachFile;
import com.server.HGUStudentUnion_server.notice.domain.Notice;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Getter
@AllArgsConstructor
public class NoticeResponse {

    private Long id;
    private AppUserProfile user;
    private Boolean pin;
    private Boolean hide;
    private String title;
    private String image;
    private String content;
    private int viewCnt;
    private List<AttachFile> files;

    public static NoticeResponse from(Notice notice, List<AttachFile> files){
        return new NoticeResponse(
                notice.getId(),
                AppUserProfile.of(notice.getUser()),
                notice.getPin(),
                notice.getHide(),
                notice.getTitle(),
                notice.getImage(),
                notice.getContent(),
                notice.getViewCnt(),
                files
        );
    }
}
