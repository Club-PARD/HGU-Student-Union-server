package com.server.HGUStudentUnion_server.AttachFile.domain.repository;

import com.server.HGUStudentUnion_server.AppUser.domain.AppUser;
import com.server.HGUStudentUnion_server.AttachFile.domain.AttachFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachFileRepo extends JpaRepository<AttachFile, Long> {
}
