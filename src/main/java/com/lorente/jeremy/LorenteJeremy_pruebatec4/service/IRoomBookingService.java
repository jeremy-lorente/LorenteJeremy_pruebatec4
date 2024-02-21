package com.lorente.jeremy.LorenteJeremy_pruebatec4.service;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.dto.RoomBookingUpdateDto;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.RoomBooking;

import java.util.List;

public interface IRoomBookingService {

    RoomBooking saveRoomBooking(RoomBooking roomBooking);

    List<RoomBooking> findAllRoomBooking();

    RoomBooking findRoomBookingByID(Long codRoomBooking);

    RoomBooking updateRoomBooking(Long codRoomBooking, RoomBookingUpdateDto romBookingDto);

    RoomBooking deleteRoomBooking(Long codRoomBooking);
}
