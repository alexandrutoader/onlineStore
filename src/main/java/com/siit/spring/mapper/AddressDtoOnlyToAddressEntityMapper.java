package com.siit.spring.mapper;

import com.siit.spring.domain.entity.Address;
import com.siit.spring.domain.model.AddressDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressDtoOnlyToAddressEntityMapper implements Converter<AddressDto, Address> {

    @Override
    public Address convert(AddressDto source) {

        return Address.builder()
                .status(source.getStatus())
                .postalCode(source.getPostalCode())
                .city(source.getCity())
                .addressName(source.getAddressName())
                .telephone(source.getTelephone())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .addressId(source.getAddressId())
                .build();
    }
}
