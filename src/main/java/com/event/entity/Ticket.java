package com.event.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer row;
    private Integer number;

}
