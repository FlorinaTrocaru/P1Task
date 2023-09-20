package com.event.repository;

import com.event.dto.SeatDTO;
import com.event.entity.Seat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatRepository extends CrudRepository<Seat, Integer> {
    @Query("SELECT s FROM Seat s WHERE s.room.id = :roomId")
    public List<Seat> findByRoomId(@Param("roomId") Integer roomId);
}
