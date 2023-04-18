package com.example.demo.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(path = "api/v1/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService){
        this.reservationService = reservationService;
    }
    @GetMapping
    public Set<Reservation> getReservations(){
        return reservationService.getReservations();
    }

    @PostMapping
    public void createReservation(@RequestBody ReservationPayload payload){
        reservationService.createReservation(payload);
    }

    @PutMapping
    public void updateReservation(@RequestParam Integer id, @RequestBody ReservationPayload payload){
        reservationService.updateReservation(id, payload);
    }
}
