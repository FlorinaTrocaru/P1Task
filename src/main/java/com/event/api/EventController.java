package com.event.api;

import com.event.dto.EventDTO;
import com.event.entity.Event;
import com.event.exception.EventException;
import com.event.service.EventService;
import com.event.service.impl.EventServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value = "/event")
public class EventController {
    private EventService eventService;

    public EventController(EventServiceImpl eventRoomService) {
        this.eventService = eventRoomService;
    }

    @PostMapping(value = "/roomId/{roomId}")
    public ResponseEntity<Event> createEvent(@Valid @RequestBody EventDTO eventDTO, @PathVariable Integer roomId) throws EventException {
        Event event = eventService.createEvent(eventDTO, roomId);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }
    @GetMapping
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<EventDTO>> getEvents(@RequestParam(name = "sort") String sort) throws EventException {
        List<EventDTO> events = eventService.getEvents();

        if(sort != null) {
            if(sort.equals("name")) {
                events.sort((a,b) -> a.getName().compareTo(b.getName()));
            }
        }
        return new ResponseEntity<>(events, HttpStatus.OK);
    }
    @GetMapping(value = "/event")
    @CrossOrigin(origins = "*")
    public ResponseEntity<EventDTO> getEventById(@RequestParam("id") Integer eventId) throws EventException {
        EventDTO event = eventService.getEventById(eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PatchMapping(value = "/eventId/{eventId}/roomId/{roomId}")
    public ResponseEntity<Event> updateRoom(@PathVariable Integer eventId, @PathVariable Integer roomId) throws EventException {
        Event event = eventService.updateRoom(eventId, roomId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{eventId}")
    public ResponseEntity<Event> deteleEvent(@PathVariable Integer eventId) throws EventException {
        Event event = eventService.deleteEvent(eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }


}