package com.server.HGUStudentUnion_server.suggest.domain.repository;

import com.server.HGUStudentUnion_server.suggest.domain.Suggest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestRepo extends JpaRepository<Suggest, Long> {
}
