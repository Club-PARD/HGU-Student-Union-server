package com.server.HGUStudentUnion_server.exception.oauth;

import com.server.HGUStudentUnion_server.exception.CommonException;
import org.springframework.http.HttpStatus;

public class ManagerNoAuthorizationException extends CommonException {
    public ManagerNoAuthorizationException(){
        super("허가 받지 않은 관리자입니다.", HttpStatus.FORBIDDEN);
    }

}
