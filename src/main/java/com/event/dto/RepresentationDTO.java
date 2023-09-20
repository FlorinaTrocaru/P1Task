package com.event.dto;

import com.event.entity.Event;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RepresentationDTO {

    private Integer id;
    private LocalDateTime dateTime;
    private Double price;
    private Event event;
}
