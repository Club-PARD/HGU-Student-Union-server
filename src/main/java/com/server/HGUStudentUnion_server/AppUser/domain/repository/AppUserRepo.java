package com.server.HGUStudentUnion_server.AppUser.domain.repository;

import com.server.HGUStudentUnion_server.AppUser.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepo extends JpaRepository<AppUser, Long> {
}
