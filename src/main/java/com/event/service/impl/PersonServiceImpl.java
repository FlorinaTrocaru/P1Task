package com.event.service.impl;

import com.event.dto.PersonDTO;
import com.event.entity.Person;
import com.event.entity.Reservation;
import com.event.exception.EventException;
import com.event.repository.PersonRepository;
import com.event.repository.ReservationRepository;
import com.event.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    PersonRepository personRepository;
    ReservationRepository reservationRepository;
    ModelMapper modelMapper;

    public PersonServiceImpl(PersonRepository personRepository, ReservationRepository reservationRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Person createPerson(PersonDTO personDTO) throws EventException {
        Person optionalPerson = personRepository.findByEmailOrPhoneNumber(personDTO.getEmail(), personDTO.getPhoneNumber());

        if(optionalPerson == null) {

            Person person = modelMapper.map(personDTO, Person.class);
            personRepository.save(person);
            return person;
        } else{
            throw new EventException("Aceste date sunt stocate deja pentru un utilizator!");
        }
    }
    @Override
    public PersonDTO getPerson(Integer id) throws EventException {
        Optional<Person> personOptional = personRepository.findById(id);
        Person person = personOptional.orElseThrow(() -> new EventException("Nu exista acest utilizator!"));
        PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
        return personDTO;
    }

    @Override
    public Person updatePerson(Integer id, PersonDTO personDTO)  throws EventException {
        Optional<Person> personOptional = personRepository.findById(id);
        Person person = personOptional.orElseThrow(() -> new EventException("Nu exista acest utilizator!"));

        if(personDTO.getFirstName() != null) person.setFirstName(personDTO.getFirstName());
        if(personDTO.getLastName() != null) person.setLastName(personDTO.getLastName());
        if(personDTO.getEmail() != null) person.setEmail(personDTO.getEmail());
        if(personDTO.getPhoneNumber() != null) person.setPhoneNumber(personDTO.getPhoneNumber());

        return person;
    }

    @Override
    public Person deletePerson(Integer id) throws EventException {
        Optional<Person> personOptional = personRepository.findById(id);
        Person person = personOptional.orElseThrow(() -> new EventException("Nu exista acest utilizator!"));
        List<Reservation> reservationList = reservationRepository.findByPersonId(person);
        for(Reservation r : reservationList) {
            r.setPerson(null);
        }

        personRepository.delete(person);
        return person;
    }
}
