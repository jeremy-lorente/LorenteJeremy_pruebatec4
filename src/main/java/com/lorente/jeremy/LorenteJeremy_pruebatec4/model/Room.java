package com.lorente.jeremy.LorenteJeremy_pruebatec4.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Room {
    @Id
    private String roomCode;
    private LocalDate availableFrom;
    private LocalDate availableUntil;

    private int numBed;
    private Boolean availableRoom;

    private Double pricePerNight;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    @JsonBackReference
    private Hotel hotel;

    @OneToMany(mappedBy="room",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoomBooking> roomBookings = new ArrayList<>();
}
