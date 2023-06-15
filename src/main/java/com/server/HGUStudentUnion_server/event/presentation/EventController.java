package com.server.HGUStudentUnion_server.event.presentation;

import com.server.HGUStudentUnion_server.event.application.EventService;
import com.server.HGUStudentUnion_server.event.domain.Event;
import com.server.HGUStudentUnion_server.event.presentation.request.EventRequest;
import com.server.HGUStudentUnion_server.event.presentation.request.EventUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<List<Event>> findAll(){
        List<Event> res = eventService.findAll();
        return ResponseEntity.ok(res);
    }

    @PostMapping("/events")
    public ResponseEntity<Event> save(@RequestBody EventRequest request){
        Event res = eventService.save(request);
        return ResponseEntity.ok(res);
    }

    @PatchMapping("/events/{eventId}")
    public ResponseEntity<Event> update(@PathVariable Long eventId, @RequestBody EventUpdateRequest request){
        Event res = eventService.update(eventId, request);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/events/{eventId}")
    public ResponseEntity<Long> delete(@PathVariable Long eventId){
        eventService.delete(eventId);
        return ResponseEntity.ok(eventId);
    }
}
