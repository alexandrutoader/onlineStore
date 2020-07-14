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
public class AddressDtoToAddressEntityMapper implements Converter<AddressDto, Address> {
    private final CustomerDtoOnlyToCustomerEntityMapper customerDtoOnlyToCustomerEntityMapper;

    @Override
    public Address convert(AddressDto source) {
        return Address.builder()
//                .customer(mapCustomer(source.getCustomer()))
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .telephone(source.getTelephone())
                .addressName(source.getAddressName())
                .city(source.getCity())
                .postalCode(source.getPostalCode())
                .status(source.getStatus())
                .build();
    }

    public Customer mapCustomer(CustomerDto customer) {
        return customerDtoOnlyToCustomerEntityMapper.convert(customer);
    }
}
