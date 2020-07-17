package com.siit.spring.mapper;

import com.siit.spring.domain.entity.Address;
import com.siit.spring.domain.entity.Cart;
import com.siit.spring.domain.entity.Customer;
import com.siit.spring.domain.entity.Order;
import com.siit.spring.domain.model.AddressDto;
import com.siit.spring.domain.model.CartDto;
import com.siit.spring.domain.model.CustomerDto;
import com.siit.spring.domain.model.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomerDtoOnlyToCustomerEntityMapper implements Converter<CustomerDto, Customer> {
    private final OrderDtoOnlyToOrderEntityMapper orderDtoOnlyToOrderEntityMapper;
    private final AddressDtoOnlyToAddressEntityMapper addressDtoOnlyToAddressEntityMapper;


    @Override
    public Customer convert(CustomerDto source) {
        return Customer.builder()
                .customerId(source.getCustomerId())
                .orders(mapOrders(source.getOrders()))
                .cart(mapCartDto(source.getCart()))
                .token(source.getToken())
                .telephone(source.getTelephone())
                .status(source.getStatus())
                .password(source.getPassword())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .agentId(source.getAgentId())
                .addressId(source.getAddressId())
                .addresses(mapAddresses(source.getAddresses()))
                .build();
    }

    public List<Order> mapOrders(List<OrderDto> orders) {
        return orders.stream()
                .map(orderDtoOnlyToOrderEntityMapper::convert)
                .collect(Collectors.toList());
    }

    public Cart mapCartDto(CartDto cart) {
        if (cart == null) {
            return null;
        }
        return Cart.builder()
                .status(cart.getStatus())
                .token(cart.getToken())
                .customer(mapCustomerDto(cart.getCustomer()))
                .build();
    }

    public Customer mapCustomerDto(CustomerDto customer) {
        return Customer.builder()
                .cart(mapCartDto(customer.getCart()))
                .addresses(mapAddresses(customer.getAddresses()))
                .addressId(customer.getAddressId())
                .agentId(customer.getAgentId())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .password(customer.getPassword())
                .status(customer.getStatus())
                .telephone(customer.getTelephone())
                .token(customer.getToken())
                .orders(mapOrders(customer.getOrders()))
                .build();
    }

    public List<Address> mapAddresses(List<AddressDto> addresses) {
        return addresses.stream()
                .map(addressDtoOnlyToAddressEntityMapper::convert)
                .collect(Collectors.toList());
    }
}
