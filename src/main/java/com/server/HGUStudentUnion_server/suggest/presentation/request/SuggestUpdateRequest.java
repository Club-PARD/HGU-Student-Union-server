package com.server.HGUStudentUnion_server.suggest.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SuggestUpdateRequest {
    private Boolean hide;
    private String title;
    private String content;
}
