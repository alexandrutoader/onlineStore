package com.siit.spring.mapper;

import com.siit.spring.domain.entity.Address;
import com.siit.spring.domain.entity.Customer;
import com.siit.spring.domain.model.AddressDto;
import com.siit.spring.domain.model.CustomerDto;
import com.siit.spring.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class AddressDtoToAddressEntityMapper implements Converter<AddressDto, Address> {
    private final CustomerRepository customerRepository;

    @Override
    public Address convert(AddressDto source) {

        return Address.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .telephone(source.getTelephone())
                .addressName(source.getAddressName())
                .city(source.getCity())
                .postalCode(source.getPostalCode())
                .status(source.getStatus())
                .dateAdded(LocalDateTime.now())
                .dateModified(LocalDateTime.now())
                .build();
    }
}
