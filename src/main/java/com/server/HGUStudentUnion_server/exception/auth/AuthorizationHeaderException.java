package com.server.HGUStudentUnion_server.exception.auth;

import org.springframework.http.HttpStatus;

public class AuthorizationHeaderException extends AuthorizationException {
    public AuthorizationHeaderException() {
        super("로그인이 필요한 서비스입니다.", HttpStatus.UNAUTHORIZED);
    }
}
