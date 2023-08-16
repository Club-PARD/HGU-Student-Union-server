package com.server.HGUStudentUnion_server.banner.domain.repository;

import com.server.HGUStudentUnion_server.banner.domain.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepo extends JpaRepository<Banner, Long> {
}
