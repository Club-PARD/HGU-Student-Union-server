package com.server.HGUStudentUnion_server.auth.presentation;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.server.HGUStudentUnion_server.auth.application.AuthService;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredManagerLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredSUManagerLogin;
import com.server.HGUStudentUnion_server.auth.domain.required.RequiredSuperManagerLogin;
import com.server.HGUStudentUnion_server.auth.infrastructure.JwtProvider;
import com.server.HGUStudentUnion_server.util.AuthorizationExtractor;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    private final AuthService authService;
    private final JwtProvider jwtProvider;




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (isPreflight(request)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (!(hasAnnotation(handlerMethod))) {
            return true;
        }
        String token = AuthorizationExtractor.extractAccessToken(request);

        if (handlerMethod.hasMethodAnnotation(RequiredLogin.class)) {
//            authService.checkNormalByToken(token);
            jwtProvider.validateBothToken(token);
        } else if (handlerMethod.hasMethodAnnotation(RequiredManagerLogin.class)) {
            authService.checkManagerByToken(token);
        } else if (handlerMethod.hasMethodAnnotation(RequiredSUManagerLogin.class)) {
            authService.checkSUManagerByToken(token);
        } else if (handlerMethod.hasMethodAnnotation(RequiredSuperManagerLogin.class)) {
            authService.checkSuperManagerByToken(token);
        }
        return true;
    }

    private boolean hasAnnotation(HandlerMethod handlerMethod) {
        return handlerMethod.hasMethodAnnotation(RequiredLogin.class)
                || handlerMethod.hasMethodAnnotation(RequiredSUManagerLogin.class)
                || handlerMethod.hasMethodAnnotation(RequiredManagerLogin.class)
                || handlerMethod.hasMethodAnnotation(RequiredSuperManagerLogin.class);
    }

    private boolean isPreflight(HttpServletRequest request) {
        return HttpMethod.OPTIONS.matches(request.getMethod());
    }
}