package com.lorente.jeremy.LorenteJeremy_pruebatec4.service;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.dto.RoomBookingUpdateDto;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Person;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Room;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.RoomBooking;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.repository.RoomBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomBookingService implements IRoomBookingService {

    @Autowired
    private RoomBookingRepository roomBookingRepository;
    @Autowired
    private IPersonService personService;
    @Autowired
    private IRoomService roomService;


    @Override
    public RoomBooking saveRoomBooking(RoomBooking roomBooking) {
        if (isRoomAvailable(roomBooking)) {
            List<Person> validPersons = new ArrayList<>();

            for (Person person : roomBooking.getPersons()) {
                Person existingPerson = personService.findPersonById(person.getDni());

                if (existingPerson != null) {
                    validPersons.add(existingPerson);
                }else{
                    return null;
                }
            }
            double roomPricePerNight = roomBooking.getRoom().getPricePerNight();
            long nights = roomBooking.getStayUntil().getDayOfMonth() - roomBooking.getStayFrom().getDayOfMonth();
            double totalAmount = roomPricePerNight * nights;

            roomBooking.setRoomPrice(totalAmount);

            roomBooking.setPersons(validPersons);
            return roomBookingRepository.save(roomBooking);

        } else {
            return null;
        }
    }

    @Override
    public List<RoomBooking> findAllRoomBooking() {
        return roomBookingRepository.findAll();
    }

    @Override
    public RoomBooking findRoomBookingByID(Long codRoomBooking) {
        return roomBookingRepository.findById(codRoomBooking).orElse(null);
    }

    @Override
    public RoomBooking updateRoomBooking(Long codRoomBooking, RoomBookingUpdateDto romBookingDto) {
        RoomBooking existingRoomBooking = roomBookingRepository.findById(codRoomBooking).orElse(null);

        if (existingRoomBooking != null) {
            existingRoomBooking.setRoomPrice(romBookingDto.getRoomPrice());
            return roomBookingRepository.save(existingRoomBooking);
        } else {
            return null;
        }
    }

    @Override
    public RoomBooking deleteRoomBooking(Long codRoomBooking) {
        if (existingRoomBooking(codRoomBooking)) {
            RoomBooking roomBooking = roomBookingRepository.findById(codRoomBooking).orElse(null);
            if (roomBooking != null) {
                roomBookingRepository.delete(roomBooking);
                return roomBooking;
            }
        }
        return null;
    }

    private boolean existingRoomBooking(Long codRoomBooking) {
        return roomBookingRepository.existsById(codRoomBooking);
    }

    private boolean isRoomAvailable(RoomBooking roomBooking) {
        String roomCode = roomBooking.getRoom().getRoomCode();
        Room room = roomService.findRoomById(roomCode);

        roomBooking.setRoom(room);

        if (room != null && room.getRoomCode() != null) {
            LocalDate stayFrom = roomBooking.getStayFrom();
            LocalDate stayUntil = roomBooking.getStayUntil();

            if (stayFrom != null && stayUntil != null) {
                boolean isRoomAvailableForDates = isRoomAvailableForDates(room, stayFrom, stayUntil);

                int numPersons = roomBooking.getNumPersons();
                boolean isNumPersonsValid = numPersons <= room.getNumBed();
                boolean isRoomCurrentlyAvailable = room.getAvailableRoom();

                return isRoomAvailableForDates && isNumPersonsValid && isRoomCurrentlyAvailable;
            }
        }

        return false;
    }

    private boolean isRoomAvailableForDates(Room room, LocalDate stayFrom, LocalDate stayUntil) {

        if (!room.getAvailableRoom()) {
            return false;
        }

        LocalDate availableFrom = room.getAvailableFrom();
        LocalDate availableUntil = room.getAvailableUntil();

        for (RoomBooking existingBooking : room.getRoomBookings()) {
            LocalDate existingStayFrom = existingBooking.getStayFrom();
            LocalDate existingStayUntil = existingBooking.getStayUntil();

            if ((stayFrom.isBefore(existingStayUntil) || stayFrom.isEqual(existingStayUntil))
                    && (stayUntil.isAfter(existingStayFrom) || stayUntil.isEqual(existingStayFrom))) {

                return false;
            }
        }

        return (stayFrom.isEqual(availableFrom) || (stayFrom.isAfter(availableFrom) && stayFrom.isBefore(availableUntil)))
                && (stayUntil.isEqual(availableUntil) || (stayUntil.isAfter(availableFrom) && stayUntil.isBefore(availableUntil)));
    }
}
