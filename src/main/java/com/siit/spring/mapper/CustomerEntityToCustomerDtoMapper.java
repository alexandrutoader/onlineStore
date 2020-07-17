package com.siit.spring.mapper;

import com.siit.spring.domain.entity.*;
import com.siit.spring.domain.model.*;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomerEntityToCustomerDtoMapper implements Converter<Customer, CustomerDto> {
    private final AddressEntityOnlyToAddressDtoMapper addressEntityOnlyToAddressDtoMapper;
    private final CustomerEntityOnlyToCustomerDtoMapper customerEntityOnlyToCustomerDtoMapper;
    private final OrderEntityListOnlyToOrderListDtoMapper orderEntityListOnlyToOrderListDtoMapper;

    @Override
    public CustomerDto convert(Customer source) {
        return CustomerDto.builder()
                .customerId(source.getCustomerId())
                .addresses(mapAddress(source.getAddresses()))
                .cart(mapCart(source.getCart()))
                .orders(mapOrders(source.getOrders()))
                .addressId(source.getAddressId())
                .agentId(source.getAgentId())
                .email(source.getEmail())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .password(source.getPassword())
                .status(source.getStatus())
                .telephone(source.getTelephone())
                .token(source.getToken())
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
                .customer(mapCustomer(cart.getCustomer()))
                .status(cart.getStatus())
                .token(cart.getToken())
                .build();
    }

    public CustomerDto mapCustomer(Customer customer) {
        return customerEntityOnlyToCustomerDtoMapper.convert(customer);
    }

    public List<OrderDto> mapOrders(List<Order> orders) {
        return orders.stream()
                .map(orderEntityListOnlyToOrderListDtoMapper::convert)
                .collect(Collectors.toList());
    }
}
