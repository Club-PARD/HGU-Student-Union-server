package com.server.HGUStudentUnion_server.auth.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OauthUserInfo {
    private String name;
    private String email;
}
