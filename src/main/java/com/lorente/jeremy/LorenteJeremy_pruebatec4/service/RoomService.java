package com.lorente.jeremy.LorenteJeremy_pruebatec4.service;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.dto.RoomUpdateDto;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Room;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.repository.HotelRepository;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public Room saveRoom(Room room) {
        String codHotel = room.getHotel().getHotelCode();

        if (hotelRepository.existsById(codHotel)) {
            if (existingRoom(room.getRoomCode())) {
                return null;
            }
            return roomRepository.save(room);
        } else {
            return null;
        }


    }

    @Override
    public List<Room> findAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Room findRoomById(String codRoom) {
        return roomRepository.findById(codRoom).orElse(null);
    }

    @Override
    public Room updateRoom(String codRoom, RoomUpdateDto roomUpdateDto) {
        Room existingRoom = roomRepository.findById(codRoom).orElse(null);

        if (existingRoom != null) {
            existingRoom.setAvailableFrom(roomUpdateDto.getAvailableFrom());
            existingRoom.setAvailableUntil(roomUpdateDto.getAvailableUntil());
            existingRoom.setNumBed(roomUpdateDto.getNumBed());
            existingRoom.setAvailableRoom(roomUpdateDto.getAvailableRoom());
            existingRoom.setPricePerNight(roomUpdateDto.getPricePerNight());

            return roomRepository.save(existingRoom);
        }
        return null;
    }


    @Override
    public Room deleteRoom(String codRoom) {
        if (existingRoom(codRoom)) {
            Room room = roomRepository.findById(codRoom).orElse(null);
            if (room != null) {
                roomRepository.delete(room);
                return room;
            }
        }
        return null;
    }

    private boolean existingRoom(String codRoom) {
        return roomRepository.existsById(codRoom);
    }
}
