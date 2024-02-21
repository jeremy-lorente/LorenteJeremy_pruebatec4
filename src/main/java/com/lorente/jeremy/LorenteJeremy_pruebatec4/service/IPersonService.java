package com.lorente.jeremy.LorenteJeremy_pruebatec4.service;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.dto.PersonUpdateDto;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Person;

import java.util.List;

public interface IPersonService {

    Person savePerson(Person person);

    List<Person> findAllPerson();

    Person findPersonById(String dni);

    Person updatePerson(String dniPerson, PersonUpdateDto personUpdateDto);

    Person deletePerson(String dni);


}
