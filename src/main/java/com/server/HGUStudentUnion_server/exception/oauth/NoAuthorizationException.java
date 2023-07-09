package com.server.HGUStudentUnion_server.exception.oauth;

import com.server.HGUStudentUnion_server.exception.CommonException;
import org.springframework.http.HttpStatus;

public class NoAuthorizationException extends CommonException {
    public NoAuthorizationException(){
        super("비승인 사용자입니다", HttpStatus.FORBIDDEN);
    }
}
