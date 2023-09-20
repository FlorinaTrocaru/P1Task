package com.event.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer numberOfChildren;
    private Integer numberOfStudents;
    private Integer numberOfAdults;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "reservation_id")
    private List<Ticket> tickets;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "representation_id")
    private Representation representation;

}
