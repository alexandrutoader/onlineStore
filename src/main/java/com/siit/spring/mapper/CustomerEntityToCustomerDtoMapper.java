package com.siit.spring.mapper;

import com.siit.spring.domain.entity.*;
import com.siit.spring.domain.model.*;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CustomerEntityToCustomerDtoMapper implements Converter<Customer, CustomerDto> {

    @Override
    public CustomerDto convert(Customer source) {
        return CustomerDto.builder()
                .customerId(source.getCustomerId())
                .addressId(source.getAddressId())
                .agentId(source.getAgentId())
                .email(source.getEmail())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .password(source.getPassword())
                .status(source.getStatus())
                .telephone(source.getTelephone())
                .build();
    }
}
