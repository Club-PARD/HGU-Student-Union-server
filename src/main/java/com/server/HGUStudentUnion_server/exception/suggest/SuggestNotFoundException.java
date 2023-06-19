package com.server.HGUStudentUnion_server.exception.suggest;

import org.springframework.http.HttpStatus;

public class SuggestNotFoundException extends SuggestException{
    public SuggestNotFoundException() {
        super("존재하지 않는 건의입니다.", HttpStatus.NOT_FOUND);
    }
}
