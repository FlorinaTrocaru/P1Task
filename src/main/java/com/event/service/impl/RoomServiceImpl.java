package com.event.service.impl;

import com.event.dto.RoomDTO;
import com.event.entity.Room;
import com.event.exception.EventException;
import com.event.repository.RoomRepository;
import com.event.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    RoomRepository roomRepository;
    ModelMapper modelMapper;

    public RoomServiceImpl(RoomRepository roomRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Room addRoom(RoomDTO roomDTO) throws EventException {

        Room roomOptional = roomRepository.findByName(roomDTO.getName());

        if(roomOptional == null) {
            Room room = modelMapper.map(roomDTO, Room.class);

            roomRepository.save(room);

            return room;
        } else{
            throw new EventException("Aceasta sala exista deja!");
        }

    }

    @Override
    public List<RoomDTO> getRooms() {
        Iterable<Room> list = roomRepository.findAll();
        List<RoomDTO> listDTO = new ArrayList<>();
        for(Room r : list) {
            RoomDTO roomDTO = modelMapper.map(r, RoomDTO.class);
            listDTO.add(roomDTO);
        }
        return listDTO;
    }

    @Override
    public Room updateSeats(Integer id, Integer numberOfSeats) throws EventException{
        Optional<Room> roomOptional = roomRepository.findById(id);
        Room room = roomOptional.orElseThrow(() -> new EventException("Aceasta sala nu exista!"));
        room.setNumberOfSeats(numberOfSeats);
        return room;
    }

    @Override
    public Room deleteRoom(Integer id) throws EventException{
        Optional<Room> roomOptional = roomRepository.findById(id);
        Room room = roomOptional.orElseThrow(() -> new EventException(""));
        roomRepository.delete(room);
        return room;
    }
}
