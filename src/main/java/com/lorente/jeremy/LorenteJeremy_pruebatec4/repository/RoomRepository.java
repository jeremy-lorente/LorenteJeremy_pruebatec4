package com.lorente.jeremy.LorenteJeremy_pruebatec4.repository;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

}
