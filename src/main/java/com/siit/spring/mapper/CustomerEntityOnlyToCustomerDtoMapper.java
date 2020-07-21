package com.siit.spring.mapper;

import com.siit.spring.domain.entity.Customer;
import com.siit.spring.domain.model.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CustomerEntityOnlyToCustomerDtoMapper implements Converter<Customer, CustomerDto> {

    @Override
    public CustomerDto convert(Customer source) {
        return CustomerDto.builder()
                .customerId(source.getCustomerId())
                .telephone(source.getTelephone())
                .status(source.getStatus())
                .password(source.getPassword())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .agentId(source.getAgentId())
                .addressId(source.getAddressId())
                .build();
    }
}
