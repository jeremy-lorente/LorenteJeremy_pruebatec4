package com.lorente.jeremy.LorenteJeremy_pruebatec4.service;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.dto.FlightBookingUpdateDto;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Flight;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.FlightBooking;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Person;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.repository.FlightBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightBookingService implements IFlightBookingService {

    @Autowired
    private FlightBookingRepository flightBookingRepository;
    @Autowired
    private IPersonService personService;
    @Autowired
    private IFlightService flightService;

    @Override
    public FlightBooking saveFlightBooking(FlightBooking flightBooking) {
        if (isFlightAvailable(flightBooking)) {
            List<Person> validPersons = new ArrayList<>();
            for (Person person : flightBooking.getPersons()) {
                Person existingPerson = personService.findPersonById(person.getDni());

                if (existingPerson != null) {
                    validPersons.add(existingPerson);
                }else{
                    return null;
                }
            }

            double seatPrice = flightBooking.getFlight().getSeatPrice();
            String seatType = flightBooking.getSeatType();
            if ("business".equalsIgnoreCase(seatType)) {
                seatPrice *= 2;
            }
            double totalBookingPrice = seatPrice * flightBooking.getNumPersons();

            flightBooking.setTotalPrice(totalBookingPrice);

            flightBooking.setPersons(validPersons);
            return flightBookingRepository.save(flightBooking);
        } else {
            return null;
        }
    }

    @Override
    public List<FlightBooking> findAllFlightBooking() {
        return flightBookingRepository.findAll();
    }

    @Override
    public FlightBooking findFlightBookingById(Long codFlightBooking) {
        return flightBookingRepository.findById(codFlightBooking).orElse(null);
    }

    @Override
    public FlightBooking updateFlightBooking(Long codFlightBooking, FlightBookingUpdateDto flightBookingDto) {
        FlightBooking existingFlightBooking = flightBookingRepository.findById(codFlightBooking).orElse(null);

        if (existingFlightBooking != null) {
            existingFlightBooking.setSeatType(flightBookingDto.getSeatType());
            existingFlightBooking.setTotalPrice(flightBookingDto.getSeatPrice());
            return flightBookingRepository.save(existingFlightBooking);
        } else {

            return null;
        }
    }

    @Override
    public FlightBooking deleteFlightBooking(Long codFlightBooking) {
        if (existingFlightBooking(codFlightBooking)) {
            FlightBooking flightBooking = flightBookingRepository.findById(codFlightBooking).orElse(null);
            if (flightBooking != null) {
                flightBookingRepository.delete(flightBooking);
                return flightBooking;
            }
        }
        return null;
    }

    private boolean existingFlightBooking(Long codFlightBooking) {
        return flightBookingRepository.existsById(codFlightBooking);
    }

    private boolean isFlightAvailable(FlightBooking flightBooking) {
        String flightCode = flightBooking.getFlight().getFlightCode();
        Flight flight = flightService.findFlightById(flightCode);

        flightBooking.setFlight(flight);
        if (flight != null && flight.getFlightCode() != null) {
            LocalDate flightDate = flightBooking.getFlightDate();

            if (flightDate != null) {
                boolean isFlightAvailableForDate = isFlightAvailableForDate(flight, flightDate);

                int numPersons = flightBooking.getNumPersons();
                boolean isNumPersonsValid = numPersons <= flight.getMaxPassengers();

                return isFlightAvailableForDate && isNumPersonsValid;
            }
        }
        return false;
    }

    private boolean isFlightAvailableForDate(Flight flight, LocalDate flightDate) {
        if (flight != null && flight.getFlightCode() != null) {
            LocalDate availableFrom = flight.getFlightDate();
            return flightDate.isEqual(availableFrom) || flightDate.isAfter(availableFrom);
        }
        return false;
    }
}