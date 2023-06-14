package com.server.HGUStudentUnion_server.exception.shareNotice;

import com.server.HGUStudentUnion_server.exception.CommonException;
import org.springframework.http.HttpStatus;

public class ShareNoticeException extends CommonException {
    protected ShareNoticeException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
