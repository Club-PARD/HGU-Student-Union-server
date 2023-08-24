package com.server.HGUStudentUnion_server.attachFile.domain.repository;

import com.server.HGUStudentUnion_server.attachFile.domain.AttachFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachFileRepo extends JpaRepository<AttachFile, Long> {


    @Query("select f from AttachFile f where f.notice.id=:noticeId")
    List<AttachFile> findByNoticeId(Long noticeId);
}
