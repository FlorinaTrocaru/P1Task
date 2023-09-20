package com.event.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;
    private String name;

    @ManyToOne
    @JoinColumn(name="event_room_id")
    private Room room;
}
