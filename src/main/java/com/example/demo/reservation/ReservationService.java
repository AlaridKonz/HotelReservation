package com.example.demo.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    private final ReservationStorage reservationStorage;

    @Autowired
    public ReservationService(ReservationStorage reservationStorage){
        this.reservationStorage = reservationStorage;
    }

    public Set<Reservation> getReservations(){
        return reservationStorage.getAllReservations();
    }

    public void createReservation(ReservationPayload payload){
        Reservation newReservation = new Reservation(
                reservationStorage.getNextIndex(),
                payload.getClientFullName(),
                payload.getRoomNumber(),
                payload.getReservationDates());

        reservationStorage.createReservation(newReservation);
    }

    public void updateReservation(Integer id, ReservationPayload payload){
        reservationStorage.updateReservation(id, payload);
    }
}
