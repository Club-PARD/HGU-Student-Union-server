package com.server.HGUStudentUnion_server.exception.shareNotice;

import org.springframework.http.HttpStatus;

public class ShareNoticeNotFoundException extends ShareNoticeException{

    public ShareNoticeNotFoundException() {
        super("존재하지 않는 자료글입니다.", HttpStatus.NOT_FOUND);
    }
}
