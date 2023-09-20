package com.event.service.impl;

import com.event.dto.TicketDTO;
import com.event.entity.Ticket;
import com.event.repository.TicketRepository;
import com.event.service.TicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    @Override
    public List<Ticket> getTickets(Integer representationId) {
        return ticketRepository.getRepresentationTickets(representationId);
    }
}
