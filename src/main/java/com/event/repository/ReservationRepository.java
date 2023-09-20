package com.event.repository;

import com.event.dto.TicketDTO;
import com.event.entity.Person;
import com.event.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    @Query("SELECT r.id FROM Reservation r")
    public List<Integer> getReservations();
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.person.id = :personId AND r.representation.id = :representationId")
    public Integer getNumberOfReservations(@Param("personId") Integer personId, @Param("representationId") Integer representationId);
    public List<Reservation> findByPersonId(Person person);

}
