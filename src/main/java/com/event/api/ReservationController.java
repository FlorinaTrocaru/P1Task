package com.event.api;

import com.event.dto.ReservationDTO;
import com.event.entity.Reservation;
import com.event.exception.EventException;
import com.event.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/reservation")
@CrossOrigin(origins = "*")
public class ReservationController {
    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping(value = "/person/{personId}/representation/{representationId}")
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDTO reservationDTO, @PathVariable Integer personId, @PathVariable Integer representationId) throws EventException {
        Reservation reservation = reservationService.createReservation(reservationDTO, personId, representationId);
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }
    @GetMapping(value = "/{reservationId}")
    public ResponseEntity<ReservationDTO> getInformationAboutReservation(@PathVariable Integer reservationId) throws EventException{
        ReservationDTO reservationDTO = reservationService.getInformationAboutReservation(reservationId);
        return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/price/reservation/{reservationId}")
    public ResponseEntity<Double> calculatePrice(@PathVariable Integer reservationId) throws EventException{
        Double totalPrice = reservationService.calculatePrice(reservationId);
        return new ResponseEntity<>(totalPrice, HttpStatus.OK );
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable Integer id) throws EventException{
        Reservation reservation = reservationService.deleteReservation(id);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }
}
