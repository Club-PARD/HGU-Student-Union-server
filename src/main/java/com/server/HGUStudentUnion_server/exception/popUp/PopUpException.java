package com.server.HGUStudentUnion_server.exception.popUp;

import com.server.HGUStudentUnion_server.exception.CommonException;
import org.springframework.http.HttpStatus;

public class PopUpException extends CommonException {
    protected PopUpException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
