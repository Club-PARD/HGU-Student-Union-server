package com.server.HGUStudentUnion_server.appUser.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserRequest {
    private int auth;
    private String name;
    private String email;
}
