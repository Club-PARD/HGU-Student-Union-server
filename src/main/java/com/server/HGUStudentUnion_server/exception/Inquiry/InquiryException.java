package com.server.HGUStudentUnion_server.exception.Inquiry;

import com.server.HGUStudentUnion_server.exception.CommonException;
import org.springframework.http.HttpStatus;

public class InquiryException extends CommonException {
    protected InquiryException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
