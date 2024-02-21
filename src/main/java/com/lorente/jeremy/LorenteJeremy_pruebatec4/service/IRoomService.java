package com.lorente.jeremy.LorenteJeremy_pruebatec4.service;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.dto.HotelUpdateDto;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.dto.RoomUpdateDto;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Hotel;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IRoomService {

    Room saveRoom(Room room);

    List<Room> findAllRoom();

    Room findRoomById(String codRoom);

    Room updateRoom(String codRoom, RoomUpdateDto room);

    Room deleteRoom(String  codRoom);

}
