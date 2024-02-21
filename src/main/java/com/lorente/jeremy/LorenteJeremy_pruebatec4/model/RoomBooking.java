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
public class RoomBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int  numPersons;
    private LocalDate stayFrom;
    private LocalDate stayUntil;

    private Double roomPrice;

    @ManyToOne
    @JsonBackReference
    private Room room;

    @ManyToMany
    @JoinTable(
            name = "person_room_booking",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "room_booking_id")
    )
    private List<Person> persons = new ArrayList<>();
}
