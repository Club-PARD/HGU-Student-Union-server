package com.server.HGUStudentUnion_server.shop.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShopUpdateRequest {
    private Boolean hide;
    private String name;
    private String title;
    private String category;
    private String content;
    private String image;
}
