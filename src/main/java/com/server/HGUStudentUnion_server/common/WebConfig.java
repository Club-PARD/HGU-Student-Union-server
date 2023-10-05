package com.server.HGUStudentUnion_server.common;

import com.server.HGUStudentUnion_server.auth.presentation.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    private final LoginInterceptor loginInterceptor;
    private final UserAgentInterceptor userAgentInterceptor;
    private final LoginNormalArgumentResolver loginNormalArgumentResolver;
    private final LoginManagerArgumentResolver loginManagerArgumentResolver;
    private final LoginSUManagerArgumentResolver loginSUManagerArgumentResolver;
    private final LoginSuperManagerArgumentResolver loginSuperManagerArgumentResolver;




    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://3.35.44.84:443")
                .allowedMethods("GET", "POST", "PATCH", "DELETE", "OPTIONS") // 허용할 HTTP method
//                .allowCredentials(true) // 쿠키 인증 요청 허용
                .maxAge(3600);// 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/api/**");
        registry.addInterceptor(userAgentInterceptor).addPathPatterns("/**");

    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginNormalArgumentResolver);
        resolvers.add(loginManagerArgumentResolver);
        resolvers.add(loginSUManagerArgumentResolver);
        resolvers.add(loginSuperManagerArgumentResolver);
    }


}