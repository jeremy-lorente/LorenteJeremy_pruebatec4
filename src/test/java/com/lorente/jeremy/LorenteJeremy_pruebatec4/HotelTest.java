package com.lorente.jeremy.LorenteJeremy_pruebatec4;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Hotel;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Room;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.repository.HotelRepository;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.service.HotelService;
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

public class HotelTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    private List<Hotel> mockList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockList = new ArrayList<>();
    }

    @Test
    public void getHotels() {
        mockList.add(new Hotel("hotelCode1", "hotel1", "teruel", null));
        mockList.add(new Hotel("hotelCode2", "hotel2", "teruel", null));
        when(hotelRepository.findAll()).thenReturn(mockList);

        List<Hotel> hotelList = hotelService.findAllHotel();
        assertTrue(hotelList != null && !hotelList.isEmpty());
    }

    @Test
    public void getHotelsEmpty() {
        when(hotelRepository.findAll()).thenReturn(mockList);

        List<Hotel> hotelList = hotelService.findAllHotel();
        assertTrue(hotelList == null || hotelList.isEmpty());
    }

    @Test
    public void getHotelsBetweenDatesAndCity() {
        Hotel hotel = new Hotel("H001", "Hotel Teruel", "Teruel", new ArrayList<>());
        Room room = new Room("R001", LocalDate.parse("2024-02-15"), LocalDate.parse("2024-03-15"), 2, true, 100.0, hotel, new ArrayList<>());
        hotel.getRooms().add(room);
        mockList.add(hotel);

        when(hotelRepository.findByCityIgnoreCase("Teruel")).thenReturn(mockList);

        List<Hotel> hotelList = hotelService.findAvailableHotelsInCityForDates("Teruel", LocalDate.parse("2024-02-15"), LocalDate.parse("2024-03-15"));

        assertTrue(hotelList != null && !hotelList.isEmpty());
    }

    @Test
    public void getHotelsBetweenDatesAndCity_FAIL() {
        Hotel hotelExample = new Hotel("H001", "Hotel Teruel", "Teruel", new ArrayList<>());
        Room roomExample = new Room("R001", LocalDate.parse("2024-02-15"), LocalDate.parse("2024-03-15"), 2, true, 100.0, hotelExample, new ArrayList<>());
        hotelExample.getRooms().add(roomExample);
        mockList.add(hotelExample);

        when(hotelRepository.findByCityIgnoreCase("Teruel")).thenReturn(mockList);

        List<Hotel> hotelList = hotelService.findAvailableHotelsInCityForDates("Valencia", LocalDate.parse("2024-02-15"), LocalDate.parse("2024-03-15"));

        assertFalse(hotelList != null && !hotelList.isEmpty());
    }

}
