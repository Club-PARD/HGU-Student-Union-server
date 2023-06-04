package com.server.HGUStudentUnion_server.Event.domain.repository;

import com.server.HGUStudentUnion_server.Event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event, Long> {
}
