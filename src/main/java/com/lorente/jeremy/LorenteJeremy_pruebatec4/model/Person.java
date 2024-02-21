package com.lorente.jeremy.LorenteJeremy_pruebatec4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Person {

    @Id
    private String dni;
    private String name;
    private String lastName;

    private String email;

    @JsonIgnore
    @ManyToMany(mappedBy = "persons")
    private List<RoomBooking> roomBookings = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "persons")
    private List<FlightBooking> flightBookings = new ArrayList<>();
}
