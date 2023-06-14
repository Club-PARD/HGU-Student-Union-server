package com.server.HGUStudentUnion_server.attachFile.domain.repository;

import com.server.HGUStudentUnion_server.attachFile.domain.AttachFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachFileRepo extends JpaRepository<AttachFile, Long> {



}
