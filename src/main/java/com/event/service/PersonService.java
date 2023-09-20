package com.event.service;

import com.event.dto.PersonDTO;
import com.event.entity.Person;
import com.event.exception.EventException;

public interface PersonService {
    public Person createPerson(PersonDTO personDTO) throws EventException;
    public PersonDTO getPerson(Integer id) throws EventException;
    public Person updatePerson(Integer id, PersonDTO personDTO) throws EventException;
    public Person deletePerson(Integer id) throws EventException;
}
