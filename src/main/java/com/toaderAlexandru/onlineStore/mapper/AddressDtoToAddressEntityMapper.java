package com.toaderAlexandru.onlineStore.mapper;

import com.toaderAlexandru.onlineStore.domain.entity.Address;
import com.toaderAlexandru.onlineStore.domain.entity.Customer;
import com.toaderAlexandru.onlineStore.domain.model.AddressDto;
import com.toaderAlexandru.onlineStore.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
                .dateAdded(LocalDateTime.now())
                .dateModified(LocalDateTime.now())
                .build();
    }
}
