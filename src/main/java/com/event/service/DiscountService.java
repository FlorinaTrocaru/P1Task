package com.event.service;

import com.event.dto.DiscountDTO;
import com.event.entity.Discount;
import com.event.exception.EventException;

public interface DiscountService {
    public Discount createDiscount(DiscountDTO discountDTO);
    public Discount updateDiscount(Integer id, DiscountDTO discountDTO) throws EventException;
}
