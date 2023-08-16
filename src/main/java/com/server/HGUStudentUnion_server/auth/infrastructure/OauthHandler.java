package com.server.HGUStudentUnion_server.auth.infrastructure;

import com.server.HGUStudentUnion_server.auth.domain.Member;
import com.server.HGUStudentUnion_server.auth.domain.OauthAttributes;
import com.server.HGUStudentUnion_server.auth.domain.OauthProvider;
import com.server.HGUStudentUnion_server.auth.domain.OauthUserInfo;
import com.server.HGUStudentUnion_server.exception.UnsupportedOauthProviderException;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class OauthHandler {
    private final Map<String, OauthProvider> oauthProviders;
    private final ApiRequester apiRequester;

    public OauthUserInfo getUserInfoFromCode(String oauthProvider, String code, Member member) {
        OauthProvider oauth = getOauthProvider(oauthProvider);
        Map<String, Object> attributes = apiRequester.getUserInfo(code, oauth, member);

        return OauthAttributes.extract(oauthProvider, attributes);
    }

    private OauthProvider getOauthProvider(String oauthProvider) {
            try {
                if (!oauthProviders.containsKey(oauthProvider))
                    throw new UnsupportedOauthProviderException();
            } catch (UnsupportedOauthProviderException e) {
                throw new RuntimeException(e);
            }

        return oauthProviders.get(oauthProvider);
    }
}