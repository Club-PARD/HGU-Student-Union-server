package com.server.HGUStudentUnion_server.exception.auth;

import com.server.HGUStudentUnion_server.exception.HGULoginException;
import org.springframework.http.HttpStatus;

public abstract class AuthorizationException extends HGULoginException {

    protected AuthorizationException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
