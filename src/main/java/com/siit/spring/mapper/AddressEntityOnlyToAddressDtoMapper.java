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
public class AddressEntityOnlyToAddressDtoMapper implements Converter<Address, AddressDto> {
//    private final CustomerEntityOnlyToCustomerDtoMapper customerEntityOnlyToCustomerDtoMapper;
    @Override
    public AddressDto convert(Address source) {
        return AddressDto.builder()
                .status(source.getStatus())
                .postalCode(source.getPostalCode())
                .city(source.getCity())
                .addressName(source.getAddressName())
                .telephone(source.getTelephone())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .addressId(source.getAddressId())
//                .customer(mapCustomer(source.getCustomer()))
                .build();
    }

//    public CustomerDto mapCustomer(Customer customer) {
//        return customerEntityOnlyToCustomerDtoMapper.convert(customer);
//    }
}
