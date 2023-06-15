package com.server.HGUStudentUnion_server.exception.event;

import com.server.HGUStudentUnion_server.exception.CommonException;
import org.springframework.http.HttpStatus;

public class EventException extends CommonException {
    protected EventException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
