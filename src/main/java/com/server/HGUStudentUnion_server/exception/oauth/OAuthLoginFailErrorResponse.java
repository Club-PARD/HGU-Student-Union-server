package com.server.HGUStudentUnion_server.exception.oauth;

import com.server.HGUStudentUnion_server.exception.ExceptionResponse;
import lombok.Getter;

@Getter
public class OAuthLoginFailErrorResponse extends ExceptionResponse {
    private final String email;

    private OAuthLoginFailErrorResponse(String message, String email) {
        super(message);
        this.email = email;
    }

    public static OAuthLoginFailErrorResponse from(NoSuchOAuthMemberException exception) {
        return new OAuthLoginFailErrorResponse(exception.getMessage(), exception.getEmail());
    }
}
