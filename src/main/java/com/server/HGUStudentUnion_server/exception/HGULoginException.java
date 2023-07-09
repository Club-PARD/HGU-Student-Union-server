package com.server.HGUStudentUnion_server.exception;

import org.springframework.http.HttpStatus;

public class HGULoginException extends RuntimeException {
    private final HttpStatus httpStatus;

    public HGULoginException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
