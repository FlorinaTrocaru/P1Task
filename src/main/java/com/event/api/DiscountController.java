package com.event.api;

import com.event.dto.DiscountDTO;
import com.event.entity.Discount;
import com.event.exception.EventException;
import com.event.service.DiscountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/discount")
public class DiscountController {

    DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping
    public ResponseEntity<Discount> createDiscount(@RequestBody DiscountDTO discountDTO){
        Discount discount = discountService.createDiscount(discountDTO);
        return new ResponseEntity<>(discount, HttpStatus.CREATED);
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Discount> updateDiscount(@PathVariable Integer id, @RequestBody DiscountDTO discountDTO) throws EventException {
        Discount discount = discountService.updateDiscount(id, discountDTO);
        return new ResponseEntity<>(discount, HttpStatus.OK);
    }
}
