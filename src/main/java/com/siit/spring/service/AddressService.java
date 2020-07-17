package com.siit.spring.service;

import com.siit.spring.domain.entity.Address;
import com.siit.spring.domain.entity.Customer;
import com.siit.spring.domain.model.AddressDto;
import com.siit.spring.domain.model.CustomerDto;
import com.siit.spring.exception.AddressNotFoundException;
import com.siit.spring.mapper.AddressDtoToAddressEntityMapper;
import com.siit.spring.mapper.AddressEntityToAddressDtoMapper;
import com.siit.spring.mapper.CustomerDtoOnlyToCustomerEntityMapper;
import com.siit.spring.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    private final AddressDtoToAddressEntityMapper addressDtoToAddressEntityMapper;
    private final AddressEntityToAddressDtoMapper addressEntityToAddressDtoMapper;
    private final CustomerDtoOnlyToCustomerEntityMapper customerDtoOnlyToCustomerEntityMapper;

    private final CustomerService customerService;

    public AddressDto create(AddressDto addressDto) {
        CustomerDto customerDto = customerService.findById(addressDto.getCustomerId());
        Customer customer = customerDtoOnlyToCustomerEntityMapper.convert(customerDto);
        Address address = addressDtoToAddressEntityMapper.convert(addressDto);

        if (null == address) {
            throw new NullPointerException("Address cannot be null! Please provide the address!");
        }

        if (null == customer) {
            throw new NullPointerException("Customer cannot be null! Please provide customer_id!");
        }

        address.setCustomer(customer);
        addressRepository.save(address);
        return addressEntityToAddressDtoMapper.convert(address);
    }

    public AddressDto findById(long addressId) {
        return addressRepository.findById(addressId)
                .map(addressEntityToAddressDtoMapper::convert)
                .orElseThrow(() -> new AddressNotFoundException("The address with provided id cannot be found!"));
    }


    public List<AddressDto> getAll() {
        return addressRepository.getAll()
                .stream()
                .map(addressEntityToAddressDtoMapper::convert)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        Address existingEntity = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("The address with provided id cannot be found!"));

        addressRepository.delete(existingEntity);
    }

    @Transactional
    public void updateTransactional(AddressDto addressDto) {
        Address address = addressRepository.findById(addressDto.getAddressId())
                .orElseThrow(() -> new AddressNotFoundException("The Address with id provided cannot be found"));

        updateFields(address, addressDto);
    }

    private void updateFields(Address address, AddressDto addressDto) {
        ModelMapper modelMapper = new ModelMapper();
        Address addressDtoToAddressEntity = modelMapper.map(addressDto, Address.class);

        if (null != addressDto.getTelephone()) {
            address.setTelephone(addressDto.getTelephone());
        }

        if (null != addressDto.getFirstName()) {
            address.setFirstName(addressDto.getFirstName());
        }

        if (null != addressDto.getLastName()) {
            address.setLastName(addressDto.getLastName());
        }

        if (null != addressDto.getStatus()) {
            address.setStatus(addressDto.getStatus());
        }

        if (null != addressDto.getPostalCode()) {
            address.setPostalCode(addressDto.getPostalCode());
        }

        if (null != addressDto.getCity()) {
            address.setCity(addressDto.getCity());
        }

        if (null != addressDto.getAddressId()) {
            address.setAddressId(addressDto.getAddressId());
        }

        if (null != addressDtoToAddressEntity.getCustomer()) {
            address.setCustomer(addressDtoToAddressEntity.getCustomer());
        }

        if (null != addressDto.getAddressName()) {
            address.setAddressName(addressDto.getAddressName());
        }
    }
}