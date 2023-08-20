package com.server.HGUStudentUnion_server.appUser.domain;

import com.server.HGUStudentUnion_server.appUser.presentation.request.AdminRequest;
import com.server.HGUStudentUnion_server.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE Admin SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Admin extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String department;
    private String position;

    public void update(AdminRequest request){
        this.department = request.getDepartment();
        this.position = request.getPosition();
    }
}
