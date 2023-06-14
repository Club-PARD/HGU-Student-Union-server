package com.server.HGUStudentUnion_server.appUser.domain.repository;

import com.server.HGUStudentUnion_server.appUser.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {
}
