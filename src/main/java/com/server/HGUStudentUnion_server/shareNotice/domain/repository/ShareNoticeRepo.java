package com.server.HGUStudentUnion_server.shareNotice.domain.repository;

import com.server.HGUStudentUnion_server.shareNotice.domain.ShareNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareNoticeRepo extends JpaRepository<ShareNotice, Long> {
}
