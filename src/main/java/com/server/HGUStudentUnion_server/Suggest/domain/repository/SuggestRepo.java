package com.server.HGUStudentUnion_server.Suggest.domain.repository;

import com.server.HGUStudentUnion_server.AppUser.domain.AppUser;
import com.server.HGUStudentUnion_server.Suggest.domain.Suggest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestRepo extends JpaRepository<Suggest, Long> {
}
