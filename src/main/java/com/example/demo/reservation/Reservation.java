package com.example.demo.reservation;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Data
public class Reservation {

    @Setter(AccessLevel.PRIVATE)
    private Integer id;

    private String clientFullName;
    private Integer roomNumber;
    private List<LocalDate> reservationDates;

    public Reservation(Integer id, String clientFullName, Integer roomNumber, List<LocalDate> reservationDates){
        this.id = id;
        this.clientFullName = clientFullName;
        this.roomNumber = roomNumber;
        this.reservationDates = reservationDates;
    }



}
