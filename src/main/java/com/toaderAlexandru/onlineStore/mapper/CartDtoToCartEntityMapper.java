package com.toaderAlexandru.onlineStore.mapper;

import com.toaderAlexandru.onlineStore.domain.entity.Cart;
import com.toaderAlexandru.onlineStore.domain.entity.Customer;
import com.toaderAlexandru.onlineStore.domain.model.CartDto;
import com.toaderAlexandru.onlineStore.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class CartDtoToCartEntityMapper implements Converter<CartDto, Cart> {
    private final CustomerRepository customerRepository;

    @Override
    public Cart convert(CartDto source) {
        Customer customer = null;
        if (null != source.getCustomerId()) {
            customer = customerRepository.findById(source.getCustomerId()).orElse(null);
        }

        return Cart.builder()
                .customer(customer)
                .status(source.getStatus())
                .token(source.getToken())
                .dateAdded(LocalDateTime.now())
                .dateModified(LocalDateTime.now())
                .build();
    }
}
