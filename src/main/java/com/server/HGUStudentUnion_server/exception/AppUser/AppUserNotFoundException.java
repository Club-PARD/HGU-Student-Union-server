package com.server.HGUStudentUnion_server.exception.AppUser;

import org.springframework.http.HttpStatus;

public class AppUserNotFoundException extends AppUserException{
    public AppUserNotFoundException(){
        super("존재하지 않는 유저입니다.", HttpStatus.NOT_FOUND);
    }

}
