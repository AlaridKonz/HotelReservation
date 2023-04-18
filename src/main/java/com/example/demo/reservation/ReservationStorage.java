package com.example.demo.reservation;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ReservationStorage {
    private final Set<Reservation> reservations;

    public ReservationStorage(){
        reservations = new HashSet<>();
        Reservation r = new Reservation(0, "Joe", 5, List.of(LocalDate.now()));
        reservations.add(r);
    }

    public Set<Reservation> getAllReservations(){
        return reservations;
    }

    public void createReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public Reservation getReservation(Integer id){
        return reservations.stream().filter(reservation -> reservation.getId() == id).findFirst().get();
    }

    public void updateReservation(Integer id, ReservationPayload payload){
        Reservation r = getReservation(id);
        r.setClientFullName(payload.getClientFullName());
        r.setRoomNumber(payload.getRoomNumber());
        r.setReservationDates(payload.getReservationDates());
    }



    private void readReservationsFromFile(){
        // TODO
    }

    public Integer getNextIndex(){
        return reservations.size();
    }

}
