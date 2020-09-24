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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomerDtoOnlyToCustomerEntityMapper implements Converter<CustomerDto, Customer> {
    private final OrderDtoOnlyToOrderEntityMapper orderDtoOnlyToOrderEntityMapper;
    private final AddressDtoOnlyToAddressEntityMapper addressDtoOnlyToAddressEntityMapper;
    private final CartDtoToCartEntityMapper cartDtoToCartEntityMapper;

    @Override
    public Customer convert(CustomerDto source) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        String encodedPassword = bCryptPasswordEncoder.encode(source.getPassword());

        List<AddressDto> addressDtos = new ArrayList<>();
        List<OrderDto> orderDtos = new ArrayList<>();
        List<CartDto> cartDtos = new ArrayList<>();
        if (null != source.getAddresses()) {
            addressDtos = source.getAddresses();
        }

        if (null != source.getOrders()) {
            orderDtos = source.getOrders();
        }

        if (null != source.getCarts()) {
            cartDtos = source.getCarts();
        }

        return Customer.builder()
                .customerId(source.getCustomerId())
                .telephone(source.getTelephone())
                .status(source.getStatus())
                .password(encodedPassword)
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .agentId(source.getAgentId())
                .addresses(mapAddresses(addressDtos))
                .orders(mapOrders(orderDtos))
                .cart(mapCarts(source.getCarts()))
                .dateAdded(LocalDateTime.now())
                .dateModified(LocalDateTime.now())
                .build();
    }

    public List<Order> mapOrders(List<OrderDto> orders) {
        if (orders.isEmpty()) {
            return new ArrayList<>();
        }
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
                .build();
    }

    public List<Address> mapAddresses(List<AddressDto> addresses) {
        if (addresses.isEmpty()) {
            return new ArrayList<>();
        }

        return addresses.stream()
                .map(addressDtoOnlyToAddressEntityMapper::convert)
                .collect(Collectors.toList());
    }

    public List<Cart> mapCarts(List<CartDto> carts) {
        if (carts.isEmpty()) {
            return new ArrayList<>();
        }

        return carts.stream()
                .map(cartDtoToCartEntityMapper::convert)
                .collect(Collectors.toList());
    }
}
