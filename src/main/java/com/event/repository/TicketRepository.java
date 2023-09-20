package com.event.repository;

import com.event.dto.TicketDTO;
import com.event.entity.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Integer> {
    @Query(value = "SELECT * FROM Ticket WHERE reservation_id IN (SELECT reservation_id FROM Reservation WHERE representation_id = :representationId)", nativeQuery = true)
    public List<Ticket> getRepresentationTickets(@Param("representationId") Integer representationId);
}
