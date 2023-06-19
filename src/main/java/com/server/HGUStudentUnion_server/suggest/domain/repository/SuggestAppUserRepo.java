package com.server.HGUStudentUnion_server.suggest.domain.repository;

import com.server.HGUStudentUnion_server.suggest.domain.SuggestAppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestAppUserRepo extends JpaRepository<SuggestAppUser, Long> {
}
