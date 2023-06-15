package com.server.HGUStudentUnion_server.exception.event;

import com.server.HGUStudentUnion_server.event.domain.Event;
import org.springframework.http.HttpStatus;

public class EventNotFoundException extends EventException {
    public EventNotFoundException() {
        super("존재하지 않는 이벤트입니다.", HttpStatus.NOT_FOUND);
    }
}
