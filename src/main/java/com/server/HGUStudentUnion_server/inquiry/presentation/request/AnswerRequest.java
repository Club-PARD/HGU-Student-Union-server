package com.server.HGUStudentUnion_server.inquiry.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRequest {
    private Long userId;
    private String answer;
}
