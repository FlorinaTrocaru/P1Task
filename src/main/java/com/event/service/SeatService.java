package com.event.service;

import com.event.dto.SeatDTO;
import com.event.entity.Seat;
import com.event.exception.EventException;

import java.util.List;

public interface SeatService {
    public List<Seat> createSeat(Integer row, Integer numberOfSeats, Integer roomId);
    public List<SeatDTO> getRoomSeats(Integer roomId);
    public List<SeatDTO> getRepresentationSeats(Integer repId);
    public Seat deleteSeat(Integer id) throws EventException;
}
