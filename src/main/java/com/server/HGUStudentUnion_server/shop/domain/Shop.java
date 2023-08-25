package com.server.HGUStudentUnion_server.shop.domain;

import com.server.HGUStudentUnion_server.common.BaseEntity;
import com.server.HGUStudentUnion_server.shop.presentation.request.ShopRequest;
import com.server.HGUStudentUnion_server.shop.presentation.request.ShopUpdateRequest;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE Shop SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Shop extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean hide;
    private Boolean pin;
    private String name;
    private String title;
    private String category;
    @Column(length = 5555)
    private String content;
    private String image;
    private int viewCnt;

    public static Shop from(ShopRequest request){
        return Shop.builder()
                .hide(false)
                .pin(request.getPin())
                .name(request.getName())
                .category(request.getCategory())
                .title(request.getTitle())
                .content(request.getContent())
                .image(request.getImage())
                .viewCnt(0)
                .build();
    }

    public void update(ShopUpdateRequest request){
        this.hide = request.getHide();
        this.pin = request.getPin();
        this.name = request.getName();
        this.category = request.getCategory();
        this.content = request.getContent();
        this.title = request.getTitle();
        this.image = request.getImage();
    }

    public void viewCnt() {
        this.viewCnt = this.viewCnt + 1;
    }
}
