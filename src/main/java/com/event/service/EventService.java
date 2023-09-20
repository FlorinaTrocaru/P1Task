package com.event.service;

import com.event.dto.EventDTO;
import com.event.entity.Event;
import com.event.exception.EventException;

import java.util.List;

public interface EventService {

    public Event createEvent(EventDTO eventDTO, Integer roomId) throws EventException;
    public List<EventDTO> getEvents() throws EventException;
    public EventDTO getEventById(Integer id) throws EventException;
    public Event updateRoom(Integer eventId, Integer roomId) throws EventException;
    public Event deleteEvent(Integer eventId) throws EventException;

}
