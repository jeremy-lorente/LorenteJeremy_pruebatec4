package com.lorente.jeremy.LorenteJeremy_pruebatec4.controller;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.dto.FlightBookingUpdateDto;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.FlightBooking;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.service.IFlightBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/agency/flight-booking")
public class FlightBookingController {

    @Autowired
    private IFlightBookingService flightBookingService;

    @PostMapping
    public ResponseEntity<FlightBooking> saveFlightBooking(@RequestBody FlightBooking flightBooking) {
        FlightBooking savedFlightBooking = flightBookingService.saveFlightBooking(flightBooking);

        if (savedFlightBooking != null) {
            return new ResponseEntity<>(savedFlightBooking, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/new")
    public ResponseEntity<String> createFlightBooking(@RequestBody FlightBooking flightBooking) {
        FlightBooking savedFlightBooking = flightBookingService.saveFlightBooking(flightBooking);

        if (savedFlightBooking != null) {
            return new ResponseEntity<>("Flight booking created successfully. Total amount: " + savedFlightBooking.getTotalPrice(), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Unable to create flight booking. Please check availability and input data.", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping
    public ResponseEntity<List<FlightBooking>> getAllFlightBookings() {
        List<FlightBooking> flightBookings = flightBookingService.findAllFlightBooking();
        if (!flightBookings.isEmpty()) {
            return new ResponseEntity<>(flightBookings, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{flightBookingId}")
    public ResponseEntity<FlightBooking> getFlightBookingById(@PathVariable long flightBookingId) {
        FlightBooking flightBooking = flightBookingService.findFlightBookingById(flightBookingId);
        if (flightBooking != null) {
            return new ResponseEntity<>(flightBooking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{flightBookingId}")
    public ResponseEntity<FlightBooking> updateFlightBooking(@PathVariable long flightBookingId, @RequestBody FlightBookingUpdateDto flightBookingUpdateDTO) {

        FlightBooking updatedFlightBooking = flightBookingService.updateFlightBooking(flightBookingId, flightBookingUpdateDTO);

        if (updatedFlightBooking != null) {
            return new ResponseEntity<>(updatedFlightBooking, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{flightBookingId}")
    public ResponseEntity<String> deleteFlightBooking(@PathVariable long flightBookingId) {
        FlightBooking flightBooking = flightBookingService.deleteFlightBooking(flightBookingId);
        if (flightBooking != null) {
            return new ResponseEntity<>("Flight booking deleted succesfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Flight booking not found", HttpStatus.NOT_FOUND);
        }
    }

}
