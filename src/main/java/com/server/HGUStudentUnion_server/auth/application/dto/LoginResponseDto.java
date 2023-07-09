package com.server.HGUStudentUnion_server.auth.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    private boolean needRegister;
    private String token;
}
