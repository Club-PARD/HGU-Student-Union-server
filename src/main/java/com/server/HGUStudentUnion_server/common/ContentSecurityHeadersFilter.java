package com.server.HGUStudentUnion_server.common;
import org.springframework.stereotype.Component;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse; // 수정된 부분
import java.io.IOException;

@Component
public class ContentSecurityHeadersFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (response instanceof HttpServletResponse) { // HttpServletResponse로 형변환
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            // JSON 형식의 Content-Type 헤더 설정
            httpServletResponse.setContentType("application/json; charset=UTF-8");

            // X-Content-Type-Options 헤더 설정
            httpServletResponse.setHeader("X-Content-Type-Options", "nosniff");
        }

        // 다음 필터 또는 요청 핸들러로 이동
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필요한 경우 초기화 작업 수행
    }

    @Override
    public void destroy() {
        // 필요한 경우 정리 작업 수행
    }
}
