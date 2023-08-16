package com.server.HGUStudentUnion_server.exception.banner;

import org.springframework.http.HttpStatus;

public class BannerNotFoundException extends BannerException {

    public BannerNotFoundException() {
        super("존재하지 않는 배너입니다.", HttpStatus.NOT_FOUND);
    }
}
