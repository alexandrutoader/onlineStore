package com.siit.spring.mapper;

import com.siit.spring.domain.entity.Address;
import com.siit.spring.domain.entity.Customer;
import com.siit.spring.domain.model.AddressDto;
import com.siit.spring.domain.model.CustomerDto;
import com.siit.spring.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressEntityOnlyToAddressDtoMapper implements Converter<Address, AddressDto> {
    private final CustomerRepository customerRepository;

    @Override
    public AddressDto convert(Address source) {
        Long customerId = null;
        if (null != source.getCustomer()) {
            Customer customer = customerRepository.findById(source.getCustomer().getCustomerId()).orElse(null);
            if (null != customer) {
                customerId = customer.getCustomerId();
            }
        }

        return AddressDto.builder()
                .status(source.getStatus())
                .postalCode(source.getPostalCode())
                .city(source.getCity())
                .addressName(source.getAddressName())
                .telephone(source.getTelephone())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .addressId(source.getAddressId())
                .customerId(customerId)
                .build();
    }
}
