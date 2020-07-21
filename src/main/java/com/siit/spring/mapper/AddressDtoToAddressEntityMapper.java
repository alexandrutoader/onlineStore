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

@Component
@AllArgsConstructor
public class AddressDtoToAddressEntityMapper implements Converter<AddressDto, Address> {
    private final CustomerRepository customerRepository;

    @Override
    public Address convert(AddressDto source) {
        Customer customer = null;
        if (null != source.getCustomerId()) {
            customer = customerRepository.findById(source.getCustomerId()).orElse(null);
        }

        return Address.builder()
                .customer(customer)
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .telephone(source.getTelephone())
                .addressName(source.getAddressName())
                .city(source.getCity())
                .postalCode(source.getPostalCode())
                .status(source.getStatus())
                .dateAdded(LocalDate.now())
                .dateModified(LocalDate.now())
                .build();
    }
}
