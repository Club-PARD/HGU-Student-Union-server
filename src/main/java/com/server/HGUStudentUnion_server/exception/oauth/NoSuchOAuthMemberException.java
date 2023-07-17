package com.server.HGUStudentUnion_server.exception.oauth;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NoSuchOAuthMemberException extends OauthException {

    private final String email;

    public NoSuchOAuthMemberException(String email) {
        super("소셜 로그인 회원이 아닙니다. 회원가입을 진행합니다.", HttpStatus.UNAUTHORIZED);
        this.email = email;
    }
}
