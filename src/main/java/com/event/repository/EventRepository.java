package com.event.repository;

import com.event.entity.Event;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface EventRepository extends CrudRepository<Event, Integer> {
    public Event findByName(String eventName);
}
