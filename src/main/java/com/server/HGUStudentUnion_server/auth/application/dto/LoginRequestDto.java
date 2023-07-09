package com.server.HGUStudentUnion_server.auth.application.dto;

import lombok.AllArgsConstructor;

import java.util.Locale;

@AllArgsConstructor
public class LoginRequestDto {
    private String oauthProvider;
    private String code;

    public String getOauthProvider() {
        return oauthProvider.toLowerCase(Locale.ROOT);
    }

    public String getCode() {
        return code;
    }
}
