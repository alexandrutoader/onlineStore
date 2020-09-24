package com.toaderAlexandru.onlineStore.service;

import com.toaderAlexandru.onlineStore.domain.entity.Customer;
import com.toaderAlexandru.onlineStore.domain.model.CustomerDto;
import com.toaderAlexandru.onlineStore.exception.CustomerNotFoundException;
import com.toaderAlexandru.onlineStore.mapper.CustomerDtoOnlyToCustomerEntityMapper;
import com.toaderAlexandru.onlineStore.mapper.CustomerEntityToCustomerDtoMapper;
import com.toaderAlexandru.onlineStore.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

        customer = customerRepository.save(customer);
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

        customer.setDateModified(LocalDateTime.now());
    }

    public void delete(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("The customer with provided id cannot be found!"));

        customerRepository.delete(customer);
    }
}
