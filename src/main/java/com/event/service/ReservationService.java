package com.event.service;

import com.event.dto.ReservationDTO;
import com.event.entity.Reservation;
import com.event.exception.EventException;

public interface ReservationService {
    public Reservation createReservation(ReservationDTO reservationDTO, Integer personId, Integer representationId) throws EventException;
    public ReservationDTO getInformationAboutReservation(Integer reservationId) throws EventException;
    public Double calculatePrice(Integer reservationId) throws EventException;
    public Reservation deleteReservation(Integer id) throws EventException;

}
