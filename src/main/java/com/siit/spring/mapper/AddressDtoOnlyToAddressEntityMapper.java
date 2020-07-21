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
public class AddressDtoOnlyToAddressEntityMapper implements Converter<AddressDto, Address> {
    private final CustomerRepository customerRepository;

    @Override
    public Address convert(AddressDto source) {
        Customer customer = null;
        if (null != source.getCustomerId()) {
            customer = customerRepository.findById(source.getCustomerId()).orElse(null);
        }

        return Address.builder()
                .status(source.getStatus())
                .postalCode(source.getPostalCode())
                .city(source.getCity())
                .addressName(source.getAddressName())
                .telephone(source.getTelephone())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .addressId(source.getAddressId())
                .customer(customer)
                .build();
    }
}
