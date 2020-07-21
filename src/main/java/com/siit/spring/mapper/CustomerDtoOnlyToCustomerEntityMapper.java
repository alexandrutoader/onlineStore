package com.siit.spring.mapper;

import com.siit.spring.domain.entity.Customer;
import com.siit.spring.domain.model.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class CustomerDtoOnlyToCustomerEntityMapper implements Converter<CustomerDto, Customer> {

    @Override
    public Customer convert(CustomerDto source) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        String encodedPassword = bCryptPasswordEncoder.encode(source.getPassword());

        return Customer.builder()
                .customerId(source.getCustomerId())
                .telephone(source.getTelephone())
                .status(source.getStatus())
                .password(encodedPassword)
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .agentId(source.getAgentId())
                .addressId(source.getAddressId())
                .dateAdded(LocalDate.now())
                .dateModified(LocalDate.now())
                .build();
    }
}
