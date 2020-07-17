package com.siit.spring.mapper;

import com.siit.spring.domain.entity.Address;
import com.siit.spring.domain.entity.Customer;
import com.siit.spring.domain.model.AddressDto;
import com.siit.spring.domain.model.CustomerDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressEntityToAddressDtoMapper implements Converter<Address, AddressDto> {

    private final CustomerEntityOnlyToCustomerDtoMapper customerEntityOnlyToCustomerDtoMapper;

    @Override
    public AddressDto convert(Address source) {
        return AddressDto.builder()
                .addressId(source.getAddressId())
                .customerId(source.getCustomer().getCustomerId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .telephone(source.getTelephone())
                .addressName(source.getAddressName())
                .city(source.getCity())
                .postalCode(source.getPostalCode())
                .status(source.getStatus())
                .build();
    }

    public CustomerDto mapCustomer(Customer customer) {
        return customerEntityOnlyToCustomerDtoMapper.convert(customer);
    }
}
