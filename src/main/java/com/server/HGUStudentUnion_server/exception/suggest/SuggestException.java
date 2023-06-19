package com.server.HGUStudentUnion_server.exception.suggest;

import com.server.HGUStudentUnion_server.exception.CommonException;
import org.springframework.http.HttpStatus;

public class SuggestException extends CommonException {
    protected SuggestException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
