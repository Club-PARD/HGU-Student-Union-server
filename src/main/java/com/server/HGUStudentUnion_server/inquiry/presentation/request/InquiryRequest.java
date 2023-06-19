package com.server.HGUStudentUnion_server.inquiry.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InquiryRequest {
    private Long userId;
    private Boolean access;
    private String title;
    private String content;
}
