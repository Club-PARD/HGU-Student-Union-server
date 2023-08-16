package com.server.HGUStudentUnion_server.exception.popUp;

import org.springframework.http.HttpStatus;

public class PopUpNotFoundException extends PopUpException {

    public PopUpNotFoundException() {
        super("존재하지 않는 팝업입니다.", HttpStatus.NOT_FOUND);
    }
}
