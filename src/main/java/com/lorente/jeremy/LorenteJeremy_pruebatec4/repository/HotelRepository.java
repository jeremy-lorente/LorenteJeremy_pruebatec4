package com.lorente.jeremy.LorenteJeremy_pruebatec4.repository;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {

    List<Hotel> findByCityIgnoreCase(String city);
}
