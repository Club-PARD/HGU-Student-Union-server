package com.server.HGUStudentUnion_server.exception.shop;

import org.springframework.http.HttpStatus;

public class ShopNotFoundException extends ShopException {
    public ShopNotFoundException() {
        super("존재하지 않는 한동샵입니다.", HttpStatus.NOT_FOUND);
    }
}
