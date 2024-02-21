package com.lorente.jeremy.LorenteJeremy_pruebatec4.service;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.dto.FlightBookingUpdateDto;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.FlightBooking;

import java.util.List;

public interface IFlightBookingService {
    FlightBooking saveFlightBooking(FlightBooking flightBooking);

    List<FlightBooking> findAllFlightBooking();

    FlightBooking findFlightBookingById(Long codFlightBooking);
    FlightBooking updateFlightBooking(Long codFlightBooking, FlightBookingUpdateDto flightBookingDto);
    FlightBooking deleteFlightBooking(Long codFlightBooking);

}
