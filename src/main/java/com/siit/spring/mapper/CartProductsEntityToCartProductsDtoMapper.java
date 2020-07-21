package com.siit.spring.mapper;

import com.siit.spring.domain.entity.CartProducts;
import com.siit.spring.domain.model.CartProductsDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartProductsEntityToCartProductsDtoMapper implements Converter<CartProducts, CartProductsDto> {
    @Override
    public CartProductsDto convert(CartProducts source) {
        return CartProductsDto.builder()
                .quantity(source.getQuantity())
                .status(source.getStatus())
                .cartId(source.getCart().getId())
                .productId(source.getProduct().getProductId())
                .build();
    }
}
