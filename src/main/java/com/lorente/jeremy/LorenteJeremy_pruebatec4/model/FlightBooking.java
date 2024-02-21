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
public class FlightBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int  numPersons;
    private LocalDate flightDate;

    private String seatType;
    private Double totalPrice;

    @ManyToOne
    @JsonBackReference
    private Flight flight;

    @ManyToMany
    @JoinTable(
            name = "person_flight_booking",
            joinColumns = @JoinColumn(name = "flight_booking_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> persons = new ArrayList<>();

}
