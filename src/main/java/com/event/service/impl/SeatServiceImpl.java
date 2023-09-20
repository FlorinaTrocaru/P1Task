package com.event.service.impl;

import com.event.dto.SeatDTO;
import com.event.entity.Room;
import com.event.entity.Seat;
import com.event.exception.EventException;
import com.event.repository.RepresentationRepository;
import com.event.repository.RoomRepository;
import com.event.repository.SeatRepository;
import com.event.service.SeatService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SeatServiceImpl implements SeatService {

    RoomRepository roomRepository;
    SeatRepository seatRepository;
    RepresentationRepository representationRepository;
    ModelMapper modelMapper;
    public SeatServiceImpl(RoomRepository roomRepository, SeatRepository seatRepository, ModelMapper modelMapper,RepresentationRepository representationRepository) {
        this.roomRepository = roomRepository;
        this.seatRepository = seatRepository;
        this.modelMapper = modelMapper;
        this.representationRepository = representationRepository;
    }

    @Override
    public List<Seat> createSeat(Integer row, Integer numberOfSeats, Integer roomId) {

        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        Room room = optionalRoom.get();
        List<Seat> list = new ArrayList<>();

        for (int i = 1; i <= numberOfSeats; i++) {
            Seat seat = new Seat();

            seat.setRoom(room);
            seat.setRow(row);
            seat.setNumber(i);
            seatRepository.save(seat);
            list.add(seat);
        }
        return list;
    }
    @Override
    public List<SeatDTO> getRoomSeats(Integer roomId) {
        List<Seat> list = seatRepository.findByRoomId(roomId);
        List<SeatDTO> listDTO = new ArrayList<>();
        for(Seat s : list) {
            SeatDTO seatDTO = modelMapper.map(s, SeatDTO.class);
            listDTO.add(seatDTO);
        }
        return listDTO;
    }
    @Override
    public List<SeatDTO> getRepresentationSeats(Integer representationId) {
        Integer id = representationRepository.getRoom(representationId);
        return getRoomSeats(id);
    }
    @Override
    public Seat deleteSeat(Integer id) throws EventException {
        Optional<Seat> seatOptional = seatRepository.findById(id);
        Seat seat = seatOptional.orElseThrow(() -> new EventException("Acest loc nu exista!"));
        seatRepository.delete(seat);
        return seat;
    }
}
