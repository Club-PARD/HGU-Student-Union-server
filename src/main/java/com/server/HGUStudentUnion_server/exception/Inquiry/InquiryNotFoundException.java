package com.server.HGUStudentUnion_server.exception.Inquiry;

import org.springframework.http.HttpStatus;

public class InquiryNotFoundException extends InquiryException{
    public InquiryNotFoundException() {
        super("존재하지 않는 문의입니다.", HttpStatus.NOT_FOUND);
    }
}
