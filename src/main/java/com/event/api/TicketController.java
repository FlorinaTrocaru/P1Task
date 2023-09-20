package com.event.api;

import com.event.dto.TicketDTO;
import com.event.entity.Ticket;
import com.event.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {
    TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Ticket>> getTickets(@PathVariable Integer id){
        List<Ticket> list = ticketService.getTickets(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
