package com.server.HGUStudentUnion_server.common;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@RequiredArgsConstructor
public class UserAgentInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String userAgent = request.getHeader("User-Agent");

        // 신뢰할 수 있는 User Agent 값인지 확인하는 로직을 구현
        if (isValidUserAgent(userAgent)) {
            return true; // 허용
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 거부 상태코드 설정
            return false; // 거부
        }
    }

    // isValidUserAgent 메서드는 이전에 설명한 것과 동일한 방식으로 구현
    private boolean isValidUserAgent(String userAgent) {
//        String allowedPattern = ".*Chrome/.*|.*Safari/.*|.*Firefox/.*|.*Internet Explorer/.*";
        String allowedPattern = ".*Chrome/.*|.*Safari/.*|.*Firefox/.*|.*Macintosh\\s+(Intel|PPC|Mac OS X [0-9_]+(\\s+\\w+)?)\\s*|.*Windows NT [0-9]+\\.[0-9]+";
        return userAgent.matches(allowedPattern);
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//                           ModelAndView modelAndView) throws Exception {
//        // 필요하면 추가적인 후처리 작업을 수행할 수 있습니다.
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//        // 필요하면 요청 완료 후에 추가적인 작업을 수행할 수 있습니다.
//    }
}
