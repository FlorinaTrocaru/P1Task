package com.event.service.impl;

import com.event.dto.*;
import com.event.entity.*;
import com.event.exception.EventException;
import com.event.repository.*;
import com.event.service.ReservationService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    ReservationRepository reservationRepository;
    PersonRepository personRepository;
    RepresentationRepository representationRepository;
    DiscountRepository discountRepository;
    ModelMapper modelMapper;
    TicketRepository ticketRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository, PersonRepository personRepository, RepresentationRepository representationRepository, DiscountRepository discountRepository, ModelMapper modelMapper, TicketRepository ticketRepository) {
        this.reservationRepository = reservationRepository;
        this.personRepository = personRepository;
        this.representationRepository = representationRepository;
        this.discountRepository = discountRepository;
        this.modelMapper = modelMapper;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Reservation createReservation(ReservationDTO reservationDTO, Integer personId, Integer representationId) throws EventException {

        Integer numberOfReservations = reservationRepository.getNumberOfReservations(personId, representationId);

        Optional<Representation> representationOptional = representationRepository.findById(representationId);
        Representation representation = representationOptional.get();

            if(numberOfReservations == 0) {

                Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);

                Optional<Person> optionalPerson = personRepository.findById(personId);
                Person person = optionalPerson.get();

                reservation.setPerson(person);

                reservation.setRepresentation(representation);

                reservationRepository.save(reservation);

                return reservation;
            } else{
                throw new EventException("Nu puteti realiza mai multe rezervari");
            }


    }
    @Override
    public ReservationDTO getInformationAboutReservation(Integer reservationId) throws EventException{

        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        Reservation reservation = reservationOptional.orElseThrow(() -> new EventException("A aparut o eroare!"));
        ReservationDTO reservationDTO = modelMapper.map(reservation, ReservationDTO.class);

        Optional<Person> personOptional = personRepository.findById(reservation.getPerson().getId());
        Person person = personOptional.orElseThrow(() -> new EventException("A aparut o eroare!"));
        PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
        reservationDTO.setPerson(personDTO);

        Optional<Representation> eventOptional = representationRepository.findById(reservation.getRepresentation().getId());
        Representation infoEvent = eventOptional.orElseThrow(() -> new EventException("A aparut o eroare!"));
        RepresentationDTO representationDTO = modelMapper.map(infoEvent,RepresentationDTO.class);
        reservationDTO.setRepresentation(representationDTO);


        return reservationDTO;

    }
    @Override
    public Double calculatePrice(Integer reservationId) throws EventException{

        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        Reservation reservation = optionalReservation.orElseThrow(() -> new EventException("Nu exista aceasta rezervare"));

        Optional<Representation> optionalRepresentation = representationRepository.findById(reservation.getRepresentation().getId());
        Representation representation = optionalRepresentation.orElseThrow(() -> new EventException("Nu exista acest eveniment"));

        Discount discountChildOptional = discountRepository.findByPerson("child");
        Double discountChild = discountChildOptional.getDiscounts();

        Discount discountStudentOptional = discountRepository.findByPerson("student");
        Double discountStudent = discountStudentOptional.getDiscounts();

        Discount discountAdultOptional = discountRepository.findByPerson("adult");
        Double discountAdult = discountAdultOptional.getDiscounts();

        Double totalPrice = (reservation.getNumberOfChildren() - reservation.getNumberOfChildren() * discountChild/100
                + reservation.getNumberOfStudents() - reservation.getNumberOfStudents() * discountStudent/100
                + reservation.getNumberOfAdults() - reservation.getNumberOfAdults() * discountAdult/100)
                * representation.getPrice();

        return totalPrice;
    }

    @Override
    public Reservation deleteReservation(Integer id) throws EventException {

        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        Reservation reservation = reservationOptional.orElseThrow(() -> new EventException("Aceasta rezervare nu exista!"));
        for(Ticket t : reservation.getTickets()){
            ticketRepository.delete(t);
        }
        reservationRepository.delete(reservation);

        return reservation;
    }
}
