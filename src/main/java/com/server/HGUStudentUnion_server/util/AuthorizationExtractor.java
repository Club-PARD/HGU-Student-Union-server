package com.server.HGUStudentUnion_server.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Locale;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorizationExtractor {
    public static final String AUTHENTICATION_TYPE = "Bearer";
    public static final String AUTHORIZATION = "Authorization";

    public static String extractAccessToken(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders(AUTHORIZATION);
        while (headers.hasMoreElements()) {
            String value = headers.nextElement();
            if (value.toLowerCase(Locale.ROOT).startsWith(AUTHENTICATION_TYPE.toLowerCase(Locale.ROOT))) {
                return value.split(" ")[1];
            }
        }
        return "";
    }
}

