package com.example.demo.reservation;

import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
public class ReservationStorage {
    private final String saveFilePath = "./reservations";
    private Set<Reservation> reservations;

    public ReservationStorage(){
        File reservationsFile = new File(saveFilePath);
        if(reservationsFile.exists()){
            try {
                Object objectFromFile = loadFromFile();
                this.reservations = (HashSet<Reservation>) objectFromFile;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else {
            this.reservations = new HashSet<>();
            saveToLocal();
        }
    }

    public Set<Reservation> getAllReservations(){
        return reservations;
    }

    public void createReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public Reservation getReservation(Integer id){
        return reservations.stream().filter(reservation -> reservation.getId().equals(id)).findFirst().get();
    }


    public boolean reservationAlreadyExistsByRoomAndDate(Integer roomNumber, List<LocalDate> dates){
        return reservations.stream().anyMatch(reservation ->
                Objects.equals(reservation.getRoomNumber(), roomNumber) && reservation.getReservationDates().stream().anyMatch(dates::contains));
    }

    public Integer getNextIndex(){
        return reservations.size();
    }

    public void saveToLocal(){
        try(ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(saveFilePath))){
            objectOut.writeObject(reservations);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Object loadFromFile(){
        try(ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(saveFilePath))){
            return objectIn.readObject();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
