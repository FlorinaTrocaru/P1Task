package com.event.dto;

import jakarta.validation.constraints.Max;
import lombok.Data;

import java.util.List;
@Data
public class ReservationDTO {

    private Integer id;
    @Max(5)
    private Integer numberOfChildren;
    @Max(5)
    private Integer numberOfStudents;
    @Max(5)
    private Integer numberOfAdults;
    private List<TicketDTO> tickets;
    private PersonDTO person;
    private RepresentationDTO representation;

}
