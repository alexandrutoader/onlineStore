package com.toaderAlexandru.onlineStore.mapper;

import com.toaderAlexandru.onlineStore.domain.entity.Address;
import com.toaderAlexandru.onlineStore.domain.model.AddressDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressEntityToAddressDtoMapper implements Converter<Address, AddressDto> {

    @Override
    public AddressDto convert(Address source) {
        Long customerId = null;
        if (null != source.getCustomer()) {
            customerId = source.getCustomer().getCustomerId();
        }

        return AddressDto.builder()
                .addressId(source.getAddressId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .telephone(source.getTelephone())
                .addressName(source.getAddressName())
                .city(source.getCity())
                .postalCode(source.getPostalCode())
                .status(source.getStatus())
                .customerId(customerId)
                .build();
    }
}
