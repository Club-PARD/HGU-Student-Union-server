package com.server.HGUStudentUnion_server.exception.oauth;

import com.server.HGUStudentUnion_server.exception.HGULoginException;
import org.springframework.http.HttpStatus;

public abstract class OauthException extends HGULoginException {

    protected OauthException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
