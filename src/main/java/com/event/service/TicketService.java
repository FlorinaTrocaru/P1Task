package com.event.service;

import com.event.dto.TicketDTO;
import com.event.entity.Ticket;

import java.util.List;

public interface TicketService {
    public List<Ticket> getTickets(Integer representationId);
}
