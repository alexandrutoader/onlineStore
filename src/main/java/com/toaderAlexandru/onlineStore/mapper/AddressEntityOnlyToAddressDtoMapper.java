package com.toaderAlexandru.onlineStore.mapper;

import com.toaderAlexandru.onlineStore.domain.entity.Address;
import com.toaderAlexandru.onlineStore.domain.model.AddressDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressEntityOnlyToAddressDtoMapper implements Converter<Address, AddressDto> {
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
                .customerId(source.getCustomer().getCustomerId())
                .build();
    }
}
