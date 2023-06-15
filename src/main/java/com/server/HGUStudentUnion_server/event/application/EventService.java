package com.server.HGUStudentUnion_server.event.application;

import com.server.HGUStudentUnion_server.event.domain.Event;
import com.server.HGUStudentUnion_server.event.domain.repository.EventRepo;
import com.server.HGUStudentUnion_server.event.presentation.request.EventRequest;
import com.server.HGUStudentUnion_server.event.presentation.request.EventUpdateRequest;
import com.server.HGUStudentUnion_server.exception.event.EventNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepo eventRepo;

    private Event find(Long eventId){
        return eventRepo.findById(eventId).orElseThrow(EventNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<Event> findAll() {
        return eventRepo.findAll();
    }

    @Transactional
    public Event save(EventRequest request) {
        Event ret = eventRepo.save(Event.from(request));
        return ret;
    }

    @Transactional
    public Event update(Long eventId, EventUpdateRequest request) {
        Event ret = this.find(eventId);
        ret.update(request);
        return ret;
    }

    @Transactional
    public void delete(Long eventId) {
        eventRepo.deleteById(eventId);
    }
}
