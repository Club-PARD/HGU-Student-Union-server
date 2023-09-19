package com.server.HGUStudentUnion_server.auth.presentation;

import com.server.HGUStudentUnion_server.auth.application.AuthService;
import com.server.HGUStudentUnion_server.auth.application.dto.LoginRequestDto;
import com.server.HGUStudentUnion_server.auth.application.dto.LoginResponseDto;
import com.server.HGUStudentUnion_server.auth.presentation.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {




    @Autowired
    private AuthService authService;

    @GetMapping("/login/oauth/{provider}")
    public ResponseEntity<TokenResponse> login(@PathVariable String provider, @RequestParam String code) {
        LoginResponseDto loginResponse = authService.login(new LoginRequestDto(provider, code));
        return ResponseEntity.ok(TokenResponse.from(loginResponse));
    }
    @GetMapping("/adminLogin/oauth/{provider}")
    public ResponseEntity<TokenResponse> adminLogin(@PathVariable String provider, @RequestParam String code) {
        LoginResponseDto loginResponse = authService.adminLogin(new LoginRequestDto(provider, code));
        return ResponseEntity.ok(TokenResponse.from(loginResponse));
    }
}
