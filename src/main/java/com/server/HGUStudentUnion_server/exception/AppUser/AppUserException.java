package com.server.HGUStudentUnion_server.exception.AppUser;

import com.server.HGUStudentUnion_server.exception.CommonException;
import org.springframework.http.HttpStatus;

public class AppUserException extends CommonException {
    protected AppUserException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
