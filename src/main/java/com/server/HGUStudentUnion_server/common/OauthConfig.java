package com.server.HGUStudentUnion_server.common;

import com.server.HGUStudentUnion_server.auth.domain.OauthProperties;
import com.server.HGUStudentUnion_server.auth.domain.OauthProvider;
import com.server.HGUStudentUnion_server.auth.infrastructure.ApiRequester;
import com.server.HGUStudentUnion_server.auth.infrastructure.OauthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(OauthProperties.class)
public class OauthConfig {
    private final OauthProperties properties;

    @Bean
    public OauthHandler oauthHandler(){
        Map<String, OauthProvider> providers = properties.getOauthProviders();
        return new OauthHandler(providers, new ApiRequester());
    }

}
