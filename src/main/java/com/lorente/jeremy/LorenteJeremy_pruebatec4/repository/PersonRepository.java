package com.lorente.jeremy.LorenteJeremy_pruebatec4.repository;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {
}
