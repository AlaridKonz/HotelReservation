package com.example.demo.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public void createReservation(ReservationPayload payload) throws Exception {
        checkForNull                    (payload.getRoomNumber(), payload.getClientFullName(), payload.getReservationDates());
        checkForRoomDateAvailability    (payload.getRoomNumber(), payload.getReservationDates());
        checkForEmptyString             (payload.getClientFullName());
        checkForPastDates               (payload.getReservationDates());
        checkForListSizeHigherThanZero  (payload.getReservationDates());
        checkForIntHigherThanZero       (payload.getRoomNumber());

        Reservation newReservation = new Reservation(
                reservationStorage.getNextIndex(),
                payload.getClientFullName(),
                payload.getRoomNumber(),
                payload.getReservationDates());

        reservationStorage.createReservation(newReservation);

        saveChangesToLocal();
    }

    public void updateReservation(Integer id, ReservationPayload payload) throws Exception {
        checkForRoomDateAvailability(payload.getRoomNumber(), payload.getReservationDates());
        checkForEmptyString             (payload.getClientFullName());
        checkForPastDates               (payload.getReservationDates());
        checkForListSizeHigherThanZero  (payload.getReservationDates());
        checkForIntHigherThanZero       (payload.getRoomNumber());

        Reservation r = reservationStorage.getReservation(id);
        if(payload.getClientFullName() != null)
            r.setClientFullName(payload.getClientFullName());
        if(payload.getReservationDates() != null)
            r.setReservationDates(payload.getReservationDates());
        if(payload.getRoomNumber() != null)
            r.setRoomNumber(payload.getRoomNumber());

        saveChangesToLocal();
    }

    private void saveChangesToLocal(){
        reservationStorage.saveToLocal();
    }

    private void checkForEmptyString(String s) throws Exception {
        if (s == null) return;
        if(s.equals("")){
            throw new Exception("Expected a non-empty string.");
        }
    }

    private void checkForListSizeHigherThanZero(List<LocalDate> dates) throws Exception {
        if(dates == null) return;
        if(dates.size() == 0) {
            throw new Exception("Expected at least one date selected.");
        }
    }

    private void checkForPastDates(List<LocalDate> dates) throws Exception {
        if (dates == null) return;
        for(LocalDate date : dates){
            if(date.isBefore(LocalDate.now())){
                throw new Exception("Cannot make a reservation with a past date.");
            }
        }
    }

    private void checkForIntHigherThanZero(Integer n) throws Exception {
        if(n == null) return;
        if(n <= 0)
            throw new Exception("Expected an integer value higher than zero.");

    }

    private void checkForNull(Object ...os) throws Exception {
        for (Object o : os) {
            if(o == null){
                throw new Exception("Null values are not allowed. Make sure to input all necessary values.");
            }
        }
    }

    private void checkForRoomDateAvailability(Integer roomNumber, List<LocalDate> days) throws Exception {
        if(reservationStorage.reservationAlreadyExistsByRoomAndDate(roomNumber, days)){
            throw new Exception("Reservation for room " + roomNumber + " already exists on one or more of the days selected.");
        }
    }
}
