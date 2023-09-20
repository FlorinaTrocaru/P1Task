package com.event.service.impl;

import com.event.dto.DiscountDTO;
import com.event.entity.Discount;
import com.event.exception.EventException;
import com.event.repository.DiscountRepository;
import com.event.service.DiscountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DiscountServiceImpl implements DiscountService {

    DiscountRepository discountRepository;
    ModelMapper modelMapper;

    public DiscountServiceImpl(DiscountRepository discountRepository, ModelMapper modelMapper) {
        this.discountRepository = discountRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public Discount createDiscount(DiscountDTO discountDTO) {
        Discount discount = modelMapper.map(discountDTO, Discount.class);
        discountRepository.save(discount);
        return discount;
    }

    @Override
    public Discount updateDiscount(Integer id, DiscountDTO discountDTO) throws EventException {
        Optional<Discount> discountOptional = discountRepository.findById(id);
        Discount discount = discountOptional.orElseThrow(() -> new EventException("Nu exista acest discount"));
        discount.setDiscounts(discountDTO.getDiscounts());
        return discount;
    }
}
