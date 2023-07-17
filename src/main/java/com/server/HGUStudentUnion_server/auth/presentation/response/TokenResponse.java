package com.server.HGUStudentUnion_server.auth.presentation.response;

import com.server.HGUStudentUnion_server.auth.application.dto.LoginResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponse {
    private boolean needRegister;
    private String token;

    public static TokenResponse from(LoginResponseDto loginResponseDto) {
        return new TokenResponse(loginResponseDto.isNeedRegister(), loginResponseDto.getToken());
    }}
