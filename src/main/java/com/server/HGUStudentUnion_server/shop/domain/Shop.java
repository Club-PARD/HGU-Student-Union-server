package com.server.HGUStudentUnion_server.shop.domain;

import com.server.HGUStudentUnion_server.common.BaseEntity;
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
@SQLDelete(sql = "UPDATE AppUser SET deleted = true WHERE id = ?")
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
}
