package com.event.api;

import com.event.dto.PersonDTO;
import com.event.entity.Person;
import com.event.exception.EventException;
import com.event.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/person")
public class PersonController {
    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody PersonDTO personDTO) throws EventException {
        Person person = personService.createPerson(personDTO);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Integer id) throws EventException {
        PersonDTO personDTO = personService.getPerson(id);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody PersonDTO personDTO) throws EventException {
        Person person = personService.updatePerson(id, personDTO);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable Integer id) throws EventException{
        Person person = personService.deletePerson(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}
