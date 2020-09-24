package com.toaderAlexandru.onlineStore.mapper;

import com.toaderAlexandru.onlineStore.domain.entity.Address;
import com.toaderAlexandru.onlineStore.domain.entity.Cart;
import com.toaderAlexandru.onlineStore.domain.entity.Customer;
import com.toaderAlexandru.onlineStore.domain.entity.Order;
import com.toaderAlexandru.onlineStore.domain.model.AddressDto;
import com.toaderAlexandru.onlineStore.domain.model.CartDto;
import com.toaderAlexandru.onlineStore.domain.model.CustomerDto;
import com.toaderAlexandru.onlineStore.domain.model.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class CustomerEntityToCustomerDtoMapper implements Converter<Customer, CustomerDto> {
    private final AddressEntityOnlyToAddressDtoMapper addressEntityOnlyToAddressDtoMapper;
    private final OrderEntityListOnlyToOrderListDtoMapper orderEntityListOnlyToOrderListDtoMapper;
    private final CartEntityToCartDtoMapper cartEntityToCartDtoMapper;

    @Override
    public CustomerDto convert(Customer source) {
        return CustomerDto.builder()
                .customerId(source.getCustomerId())
                .agentId(source.getAgentId())
                .email(source.getEmail())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .password(source.getPassword())
                .status(source.getStatus())
                .telephone(source.getTelephone())
                .addresses(mapAddress(source.getAddresses()))
                .carts(mapCarts(source.getCart()))
                .orders(mapOrders(source.getOrders()))
                .build();
    }

    public List<AddressDto> mapAddress(List<Address>addresses) {
        return addresses.stream()
                .map(addressEntityOnlyToAddressDtoMapper::convert)
                .collect(Collectors.toList());
    }

    public CartDto mapCart(Cart cart) {
        if (cart == null) {
            return null;
        }
        return CartDto.builder()
                .status(cart.getStatus())
                .token(cart.getToken())
                .build();
    }

    public List<OrderDto> mapOrders(List<Order> orders) {
        return orders.stream()
                .map(orderEntityListOnlyToOrderListDtoMapper::convert)
                .collect(Collectors.toList());
    }

    public List<CartDto> mapCarts(List<Cart>carts) {
        return carts.stream()
                .map(cartEntityToCartDtoMapper::convert)
                .collect(Collectors.toList());
    }
}
