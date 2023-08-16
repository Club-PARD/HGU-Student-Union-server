package com.server.HGUStudentUnion_server.exception.banner;

import com.server.HGUStudentUnion_server.exception.CommonException;
import org.springframework.http.HttpStatus;

public class BannerException extends CommonException {
    protected BannerException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
