package com.server.HGUStudentUnion_server.event.domain.repository;

import com.server.HGUStudentUnion_server.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event, Long> {
}
