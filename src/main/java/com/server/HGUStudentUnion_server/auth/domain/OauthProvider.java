package com.server.HGUStudentUnion_server.auth.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OauthProvider {
    private final String clientId;
    private final String clientSecret;
    private final String studentRedirectUrl;
    private final String managerRedirectUrl;
    private final String tokenUrl;
    private final String userInfoUrl;

    public OauthProvider(OauthProperties.User user, OauthProperties.Provider provider) {
        this(user.getClientId(), user.getClientSecret(), user.getStudentRedirectUri(), user.getManagerRedirectUri(),
                provider.getTokenUri(), provider.getUserInfoUri());
    }
}