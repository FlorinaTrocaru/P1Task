package com.event.service;

import com.event.dto.RoomDTO;
import com.event.entity.Room;
import com.event.exception.EventException;

import java.util.List;

public interface RoomService {
    public Room addRoom(RoomDTO roomDTO) throws EventException;
    public List<RoomDTO> getRooms();
    public Room updateSeats(Integer id, Integer numberOfSeats) throws EventException;
    public Room deleteRoom(Integer id) throws EventException;
}
