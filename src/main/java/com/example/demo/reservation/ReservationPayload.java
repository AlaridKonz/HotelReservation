package com.example.demo.reservation;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class ReservationPayload {
    @Getter private String clientFullName;
    @Getter private Integer roomNumber;
    @Getter private List<LocalDate> reservationDates;
}
