package com.event.api;

import com.event.dto.RoomDTO;
import com.event.entity.Room;
import com.event.exception.EventException;
import com.event.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpTimeoutException;
import java.util.List;

@RestController
@RequestMapping(value = "/room")
public class RoomController {
    RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public ResponseEntity<Room> createRoom(@Valid @RequestBody RoomDTO roomDTO) throws EventException {
        Room room = roomService.addRoom(roomDTO);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<RoomDTO>> getRooms(){
        List<RoomDTO> list = roomService.getRooms();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Room> updateSeats(@PathVariable Integer id, @RequestBody RoomDTO roomDTO) throws EventException {
        Room room = roomService.updateSeats(id, roomDTO.getNumberOfSeats());
        return new ResponseEntity<>(room, HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Room> deleteRoom(@PathVariable Integer id) throws EventException {
        Room room = roomService.deleteRoom(id);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }
}
