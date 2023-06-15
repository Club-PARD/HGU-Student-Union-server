package com.server.HGUStudentUnion_server.exception.shop;

import com.server.HGUStudentUnion_server.exception.CommonException;
import org.springframework.http.HttpStatus;

public class ShopException extends CommonException {
    protected ShopException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
