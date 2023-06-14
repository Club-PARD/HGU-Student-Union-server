package com.server.HGUStudentUnion_server.notice.domain.repository;

import com.server.HGUStudentUnion_server.notice.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepo extends JpaRepository<Notice, Long> {
}
