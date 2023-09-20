package com.event.repository;


import com.event.entity.Discount;
import org.springframework.data.repository.CrudRepository;

public interface DiscountRepository extends CrudRepository<Discount, Integer> {
    public Discount findByPerson(String person);
}
