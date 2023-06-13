package com.server.HGUStudentUnion_server.exception.notice;

import com.server.HGUStudentUnion_server.exception.CommonException;
import org.springframework.http.HttpStatus;

public class NoticeException extends CommonException {

   protected NoticeException(String message, HttpStatus httpStatus){super(message, httpStatus);}
}
