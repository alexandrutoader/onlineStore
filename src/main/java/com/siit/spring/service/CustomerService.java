package com.siit.spring.service;

import com.siit.spring.domain.entity.Customer;
import com.siit.spring.domain.model.CustomerDto;
import com.siit.spring.exception.CustomerNotFoundException;
import com.siit.spring.mapper.CustomerDtoOnlyToCustomerEntityMapper;
import com.siit.spring.mapper.CustomerEntityToCustomerDtoMapper;
import com.siit.spring.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerEntityToCustomerDtoMapper customerEntityToCustomerDtoMapper;
    private final CustomerDtoOnlyToCustomerEntityMapper customerDtoOnlyToCustomerEntityMapper;

    public CustomerDto create(CustomerDto customerDto) {
        Customer customer = customerDtoOnlyToCustomerEntityMapper.convert(customerDto);

        if (null == customer) {
            throw new NullPointerException("Customer cannot be null! Please provide customer_id!");
        }

        customerRepository.save(customer);
        return customerEntityToCustomerDtoMapper.convert(customer);
    }

    public CustomerDto findById(long customerId) {
        return customerRepository.findById(customerId)
                .map(customerEntityToCustomerDtoMapper::convert)
                .orElseThrow(() -> new CustomerNotFoundException("The customer with provided id cannot be found!"));
    }

    public List<CustomerDto> getAll() {
        return customerRepository.getAll()
                .stream()
                .map(customerEntityToCustomerDtoMapper::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerDto.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("The Customer with id provided cannot be found"));

        updateFields(customer, customerDto);
    }

    private void updateFields(Customer customer, CustomerDto customerDto) {
        if (null != customerDto.getEmail()) {
            customer.setEmail(customerDto.getEmail());
        }

        if (null != customerDto.getFirstName()) {
            customer.setFirstName(customerDto.getFirstName());
        }

        if (null != customerDto.getLastName()) {
            customer.setLastName(customerDto.getLastName());
        }

        if (null != customerDto.getStatus()) {
            customer.setStatus(customerDto.getStatus());
        }

        if (null != customerDto.getTelephone()) {
            customer.setTelephone(customerDto.getTelephone());
        }

        if (null != customerDto.getPassword()) {
            int strength = 10;
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength);
            String encodedPassword = bCryptPasswordEncoder.encode(customerDto.getPassword());
            customer.setPassword(encodedPassword);
        }
    }

    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("The customer with provided id cannot be found!"));

        customerRepository.delete(customer);
    }
}
