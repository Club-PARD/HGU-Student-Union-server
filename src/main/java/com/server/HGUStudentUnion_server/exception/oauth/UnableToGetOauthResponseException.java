package com.server.HGUStudentUnion_server.exception.oauth;

import org.springframework.http.HttpStatus;

public class UnableToGetOauthResponseException extends OauthException {
    public UnableToGetOauthResponseException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
