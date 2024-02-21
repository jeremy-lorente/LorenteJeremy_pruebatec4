package com.lorente.jeremy.LorenteJeremy_pruebatec4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class HotelUpdateDto {
    private String hotelName;
    private String city;
}
