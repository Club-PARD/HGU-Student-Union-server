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
    private String name;
    private String category;
    private String content;
    private String image;

    public static Shop from(ShopRequest request){
        return Shop.builder()
                .hide(false)
                .name(request.getName())
                .category(request.getCategory())
                .content(request.getContent())
                .image(request.getImage())
                .build();
    }

    public void update(ShopUpdateRequest request){
        this.hide = request.getHide();
        this.name = request.getName();
        this.category = request.getCategory();
        this.content = request.getContent();
        this.image = request.getImage();
    }
}
