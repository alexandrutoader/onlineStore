package com.siit.spring.mapper;

import com.siit.spring.domain.entity.Cart;
import com.siit.spring.domain.model.CartDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartEntityToCartDtoMapper implements Converter<Cart, CartDto> {
    @Override
    public CartDto convert(Cart source) {
        return CartDto.builder()
                .customerId(source.getCustomer().getCustomerId())
                .status(source.getStatus())
                .token(source.getToken())
                .build();
    }
}
