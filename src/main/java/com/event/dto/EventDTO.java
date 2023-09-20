package com.event.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class EventDTO {

    private Integer id;
    private String type;
    @NotNull(message = "Adaugati un nume pentru eveniment!")
    private String name;
    private RoomDTO roomDTO;
}

