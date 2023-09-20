package com.event.repository;

import com.event.entity.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Integer> {
    public Room findByName(String roomName);
}
