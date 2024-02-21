package com.lorente.jeremy.LorenteJeremy_pruebatec4.service;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.dto.HotelUpdateDto;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Hotel;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Room;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService implements IHotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        if (existingHotel(hotel.getHotelCode())) {
            return null;
        }
        return hotelRepository.save(hotel);

    }

    @Override
    public List<Hotel> findAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel findHotelById(String codHotel) {
        return hotelRepository.findById(codHotel).orElse(null);
    }

    @Override
    public Hotel updateHotel(String codHotel, HotelUpdateDto updatedHotelDto) {

        if (existingHotel(codHotel)) {
            Hotel existingHotel = hotelRepository.findById(codHotel).orElse(null);
            if (existingHotel != null) {
                existingHotel.setHotelName(updatedHotelDto.getHotelName());
                existingHotel.setCity(updatedHotelDto.getCity());
                return hotelRepository.save(existingHotel);
            }
        }
        return null;
    }


    @Override
    public Hotel deleteHotel(String codHotel) {
        if (existingHotel(codHotel)) {
            Hotel hotel = hotelRepository.findById(codHotel).orElse(null);
            if (hotel != null && areRoomsEmpty(hotel)) {
                hotelRepository.delete(hotel);
                return hotel;
            }
        }
        return null;
    }

    @Override
    public List<Hotel> findAvailableHotelsInCityForDates(String city, LocalDate dateFrom, LocalDate dateTo) {
        return hotelRepository.findByCityIgnoreCase(city).stream().filter(hotel -> hotel.getRooms().stream().anyMatch(room -> isRoomAvailableForDates(room, dateFrom, dateTo))).collect(Collectors.toList());
    }

    private boolean isRoomAvailableForDates(Room room, LocalDate dateFrom, LocalDate dateTo) {
        return room.getAvailableFrom().isBefore(dateTo) && room.getAvailableUntil().isAfter(dateFrom);
    }

    private boolean existingHotel(String codHotel) {
        return hotelRepository.existsById(codHotel);
    }

    private boolean areRoomsEmpty(Hotel hotel) {
        return hotel.getRooms().stream().allMatch(room -> room.getRoomBookings().isEmpty());
    }

}
