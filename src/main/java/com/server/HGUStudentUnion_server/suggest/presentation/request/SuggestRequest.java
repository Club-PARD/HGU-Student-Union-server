package com.server.HGUStudentUnion_server.suggest.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SuggestRequest {
    private String title;
    private Boolean access;
    private String content;
}
