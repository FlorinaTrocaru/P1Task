package com.event.repository;

import com.event.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {

    public Person findByEmailOrPhoneNumber(String email, String phoneNumber);
}
