package com.server.HGUStudentUnion_server.appUser.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequest {
    private String name;
    private String department;
    private String position;
}
