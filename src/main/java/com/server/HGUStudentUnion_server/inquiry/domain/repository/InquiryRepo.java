package com.server.HGUStudentUnion_server.inquiry.domain.repository;

import com.server.HGUStudentUnion_server.inquiry.domain.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepo extends JpaRepository<Inquiry, Long> {
}
