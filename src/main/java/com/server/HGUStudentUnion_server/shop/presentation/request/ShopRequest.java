package com.server.HGUStudentUnion_server.shop.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShopRequest {
    private String name;
    private String category;
    private String content;
    private String image;
}
