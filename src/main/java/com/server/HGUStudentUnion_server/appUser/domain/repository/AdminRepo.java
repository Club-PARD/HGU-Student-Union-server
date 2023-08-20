package com.server.HGUStudentUnion_server.appUser.domain.repository;

import com.server.HGUStudentUnion_server.appUser.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Long> {
}
