package com.toaderAlexandru.onlineStore.mapper;

import com.toaderAlexandru.onlineStore.domain.entity.Cart;
import com.toaderAlexandru.onlineStore.domain.entity.CartProducts;
import com.toaderAlexandru.onlineStore.domain.entity.Product;
import com.toaderAlexandru.onlineStore.domain.model.CartProductsDto;
import com.toaderAlexandru.onlineStore.repository.CartRepository;
import com.toaderAlexandru.onlineStore.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CartProductsDtoToCartProductsEntityMapper implements Converter<CartProductsDto, CartProducts> {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public CartProducts convert(CartProductsDto source) {
        Cart cart = null;
        Product product = null;
        if (null != source.getCartId()) {
            cart = cartRepository.findById(source.getCartId()).orElse(null);
        }
        if (null != source.getProductId()) {
            product = productRepository.findById(source.getProductId()).orElse(null);
        }

        return CartProducts.builder()
                .quantity(source.getQuantity())
                .status(source.getStatus())
                .cart(cart)
                .product(product)
                .build();
    }
}
