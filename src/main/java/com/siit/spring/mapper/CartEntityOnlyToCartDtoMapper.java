package com.siit.spring.mapper;

import com.siit.spring.domain.entity.Cart;
import com.siit.spring.domain.entity.Customer;
import com.siit.spring.domain.model.CartDto;
import com.siit.spring.domain.model.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartEntityOnlyToCartDtoMapper implements Converter<Cart, CartDto> {
    private final CustomerEntityOnlyToCustomerDtoMapper customerEntityOnlyToCustomerDtoMapper;

    @Override
    public CartDto convert(Cart source) {
        return CartDto.builder()
                .token(source.getToken())
                .status(source.getStatus())
                .customerId(source.getCustomer().getCustomerId())
                .build();
    }

    public CustomerDto mapCustomer(Customer customer) {
        return customerEntityOnlyToCustomerDtoMapper.convert(customer);
    }
}
