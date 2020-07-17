package com.siit.spring.service;

import com.siit.spring.domain.entity.Customer;
import com.siit.spring.domain.model.CustomerDto;
import com.siit.spring.exception.AddressNotFoundException;
import com.siit.spring.mapper.CustomerEntityToCustomerDtoMapper;
import com.siit.spring.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerEntityToCustomerDtoMapper customerEntityToCustomerDtoMapper;

    public CustomerDto findById(long customerId) {
        return customerRepository.findById(customerId)
                .map((Customer customer) -> customerEntityToCustomerDtoMapper.convert(customer))
                .orElseThrow(() -> new AddressNotFoundException("The address with provided id cannot be found!"));
    }
}
