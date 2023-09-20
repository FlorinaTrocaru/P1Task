package com.event.api;

import com.event.dto.SeatDTO;
import com.event.entity.Seat;
import com.event.exception.EventException;
import com.event.service.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/seat")
public class SeatController {
    SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping(value = "/row/{row}/numberOfSeats/{numberOfSeats}/room/{roomId}")
    public ResponseEntity<List<Seat>> createSeat(@PathVariable Integer row, @PathVariable Integer numberOfSeats, @PathVariable Integer roomId){
        List<Seat> list = seatService.createSeat(row, numberOfSeats, roomId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping(value = "/room/{id}")
    public ResponseEntity<List<SeatDTO>> getSeats(@PathVariable Integer id){
        List<SeatDTO> list = seatService.getRoomSeats(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping(value = "/representation/{id}")
    public ResponseEntity<List<SeatDTO>> getRepresentationSeats(@PathVariable Integer id){
        List<SeatDTO> list = seatService.getRepresentationSeats(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Seat> deleteSeat(@PathVariable Integer id) throws EventException {
        Seat seat = seatService.deleteSeat(id);
        return new ResponseEntity<>(seat, HttpStatus.OK);
    }
}
