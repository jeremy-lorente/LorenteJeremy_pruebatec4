package com.lorente.jeremy.LorenteJeremy_pruebatec4.controller;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.dto.RoomUpdateDto;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Room;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/agency/hotel/rooms")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @PostMapping
    public ResponseEntity<Room> saveRoom(@RequestBody Room room) {
        Room savedRoom = roomService.saveRoom(room);

        if(savedRoom != null){
            return new ResponseEntity<>(savedRoom, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> rooms = roomService.findAllRoom();
        if(!rooms.isEmpty()){
            return new ResponseEntity<>(rooms, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{roomCode}")
    public ResponseEntity<Room> getRoomById(@PathVariable String roomCode) {
        Room room = roomService.findRoomById(roomCode);
        if (room != null) {
            return new ResponseEntity<>(room, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{roomCode}")
    public ResponseEntity<Room> updateRoom(@PathVariable String roomCode, @RequestBody RoomUpdateDto roomDTO) {
        Room updatedRoom = roomService.updateRoom(roomCode, roomDTO);
        if (updatedRoom != null) {
            return new ResponseEntity<>(updatedRoom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{roomCode}")
    public ResponseEntity<String> deleteRoom(@PathVariable String roomCode) {
        Room deletedRoom = roomService.deleteRoom(roomCode);
        if (deletedRoom != null) {
            return new ResponseEntity<>("Room deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Room not found",HttpStatus.NOT_FOUND);
        }
    }




}