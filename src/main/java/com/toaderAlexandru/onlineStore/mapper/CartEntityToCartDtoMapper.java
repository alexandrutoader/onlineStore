package com.toaderAlexandru.onlineStore.mapper;

import com.toaderAlexandru.onlineStore.domain.entity.Cart;
import com.toaderAlexandru.onlineStore.domain.model.CartDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartEntityToCartDtoMapper implements Converter<Cart, CartDto> {
    @Override
    public CartDto convert(Cart source) {
        Long customerId = null;
        if (null != source.getCustomer()) {
            customerId = source.getCustomer().getCustomerId();
        }

        return CartDto.builder()
                .id(source.getId())
                .customerId(customerId)
                .status(source.getStatus())
                .token(source.getToken())
                .build();
    }
}
