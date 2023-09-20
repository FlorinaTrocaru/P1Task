package com.event.service.impl;

import com.event.dto.EventDTO;
import com.event.dto.RoomDTO;
import com.event.entity.Event;
import com.event.entity.Room;
import com.event.exception.EventException;
import com.event.repository.*;

import com.event.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EventServiceImpl implements EventService {
    EventRepository eventRepository;
    RoomRepository roomRepository;
    ModelMapper modelMapper;

    public EventServiceImpl(EventRepository eventRepository, RoomRepository roomRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Event createEvent(EventDTO eventDTO, Integer roomId) throws EventException{

        Event eventOptional = eventRepository.findByName(eventDTO.getName());

        if(eventOptional == null) {

            Optional<Room> optionalRoom = roomRepository.findById(roomId);
            Room room = optionalRoom.orElseThrow(() -> new EventException("Aceasta sala nu exista"));

            Event event = modelMapper.map(eventDTO, Event.class);
            event.setRoom(room);

            eventRepository.save(event);

            return event;
        } else{
            throw new EventException("Acest eveniment exista deja!");
        }

    }
    @Override
    public List<EventDTO> getEvents() throws EventException{

        Iterable<Event> events = eventRepository.findAll();

        List<EventDTO> eventList = new ArrayList<>();

        if(!events.iterator().hasNext()) throw new EventException("Nu exista evenimente!");
        for (Event event : events) {
            Room room = event.getRoom();
            RoomDTO roomDTO = modelMapper.map(room, RoomDTO.class);
            EventDTO eventDTO = modelMapper.map(event, EventDTO.class);
            eventDTO.setRoomDTO(roomDTO);

            eventList.add(eventDTO);
        }
        return eventList;

    }
    @Override
    public EventDTO getEventById(Integer eventId) throws EventException {

        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        Event event = optionalEvent.orElseThrow(() -> new EventException("Acest eveniment nu exista"));

        EventDTO eventDTO = modelMapper.map(event, EventDTO.class);

        return eventDTO;

    }
    @Override
    public Event updateRoom(Integer eventId, Integer roomId) throws EventException{
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        Event event = optionalEvent.orElseThrow(() -> new EventException("Nu exista acet eveniment"));

        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        Room room = optionalRoom.orElseThrow(() -> new EventException("Nu exista aceasta sala"));

        event.setRoom(room);

        return event;
    }
    @Override
    public Event deleteEvent(Integer eventId) throws EventException{
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        Event event = optionalEvent.orElseThrow(() -> new EventException("Nu exista acest eveniment"));

        eventRepository.delete(event);
        return event;
    }


}
