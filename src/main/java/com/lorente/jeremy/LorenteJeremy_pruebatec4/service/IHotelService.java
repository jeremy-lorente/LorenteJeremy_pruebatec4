package com.lorente.jeremy.LorenteJeremy_pruebatec4.service;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.dto.HotelUpdateDto;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Hotel;

import java.time.LocalDate;
import java.util.List;

public interface IHotelService {


    Hotel saveHotel(Hotel hotel);

    List<Hotel> findAllHotel();

    Hotel findHotelById(String codHotel);

    Hotel updateHotel(String codHotel, HotelUpdateDto hotelUpdateDto);

    Hotel deleteHotel(String  codHotel);

    List<Hotel> findAvailableHotelsInCityForDates(String city, LocalDate parsedDateFrom, LocalDate parsedDateTo);
}
