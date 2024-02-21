package com.lorente.jeremy.LorenteJeremy_pruebatec4;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Flight;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.repository.FlightRepository;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class FlightTest {
    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    private List<Flight> mockList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockList = new ArrayList<>();
    }

    @Test
    public void getFlights() {
        mockList.add(new Flight("flightCode1", "Teruel", "Madrid", LocalDate.parse("2024-02-15"), 100, 100.00, null));
        when(flightRepository.findAll()).thenReturn(mockList);

        List<Flight> flightList = flightService.findAllFlight();
        assertTrue(flightList != null && !flightList.isEmpty());
    }

    @Test
    public void getFlightsEmpty() {
        when(flightRepository.findAll()).thenReturn(mockList);

        List<Flight> flightList = flightService.findAllFlight();
        assertTrue(flightList == null || flightList.isEmpty());

    }

    @Test
    public void getFlightsBetweenDatesAndCity(){
        Flight flight = new Flight("flightCode1", "Teruel", "Madrid", LocalDate.parse("2024-02-15"), 100, 100.00, null);

        mockList.add(flight);

        when(flightRepository.findByOriginAndDestination("Teruel","Madrid")).thenReturn(mockList);

        List<Flight> flightList = flightService.findAvailableFlightWithOriginAndDestinationForDates("Teruel","Madrid", LocalDate.parse("2024-02-15"), LocalDate.parse("2025-02-15"));

        assertTrue(flightList != null && !flightList.isEmpty());

    }


    @Test
    public void getFlightsBetweenDatesAndCity_FAIL(){
        Flight flight = new Flight("flightCode1", "Teruel", "Madrid", LocalDate.parse("2024-02-15"), 100, 100.00, null);

        mockList.add(flight);

        when(flightRepository.findByOriginAndDestination("Teruel","Madrid")).thenReturn(mockList);

        List<Flight> flightList = flightService.findAvailableFlightWithOriginAndDestinationForDates("Madrid","Sevilla", LocalDate.parse("2024-02-15"), LocalDate.parse("2025-02-15"));

        assertFalse(flightList != null && !flightList.isEmpty());

    }

}
