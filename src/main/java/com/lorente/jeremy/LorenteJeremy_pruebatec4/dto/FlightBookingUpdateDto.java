package com.lorente.jeremy.LorenteJeremy_pruebatec4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FlightBookingUpdateDto {
    private String seatType;
    private Double seatPrice;
}
