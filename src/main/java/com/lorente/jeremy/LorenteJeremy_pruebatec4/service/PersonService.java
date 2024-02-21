package com.lorente.jeremy.LorenteJeremy_pruebatec4.service;

import com.lorente.jeremy.LorenteJeremy_pruebatec4.dto.PersonUpdateDto;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.FlightBooking;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.Person;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.model.RoomBooking;
import com.lorente.jeremy.LorenteJeremy_pruebatec4.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person savePerson(Person person) {
        if (existingPerson(person.getDni())) {
            return null;
        }
        for (RoomBooking roomBooking : person.getRoomBookings()) {
            roomBooking.getPersons().add(person);
        }
        for(FlightBooking flightBooking : person.getFlightBookings()) {
            flightBooking.getPersons().add(person);
        }



        return personRepository.save(person);
    }

    @Override
    public List<Person> findAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public Person findPersonById(String dni) {
        return personRepository.findById(dni).orElse(null);
    }


    @Override
    public Person updatePerson(String dniPerson, PersonUpdateDto personDto) {
        Person existingPerson = personRepository.findById(dniPerson).orElse(null);

        if (existingPerson != null) {
            existingPerson.setName(personDto.getName());
            existingPerson.setLastName(personDto.getLastName());
            existingPerson.setEmail(personDto.getEmail());
            return personRepository.save(existingPerson);
        }
        return null;
    }

    @Override
    public Person deletePerson(String dni) {
        if (existingPerson(dni)) {
            Person person = personRepository.findById(dni).orElse(null);
            if (person != null) {
                personRepository.delete(person);
                return person;
            }
        }
        return null;
    }

    private boolean existingPerson(String dni) {
        return personRepository.existsById(dni);
    }
}
