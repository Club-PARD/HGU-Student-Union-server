package com.server.HGUStudentUnion_server.DataNotice.domain.repository;

import com.server.HGUStudentUnion_server.AppUser.domain.AppUser;
import com.server.HGUStudentUnion_server.DataNotice.domain.DataNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataNoticeRepo extends JpaRepository<DataNotice, Long> {
}
