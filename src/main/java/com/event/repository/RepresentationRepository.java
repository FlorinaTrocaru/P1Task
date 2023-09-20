package com.event.repository;

import com.event.entity.Representation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepresentationRepository extends CrudRepository<Representation, Integer> {
    @Query("SELECT r.event.room.id FROM Representation r WHERE r.id = :id")
    public Integer getRoom(@Param("id") Integer id);
    @Query("SELECT r FROM Representation r WHERE CAST(r.dateTime AS DATE) = CAST(:date AS DATE)")
    public List<Representation> findByDate(String date);
}
