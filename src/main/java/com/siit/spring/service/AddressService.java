package com.siit.spring.service;

import com.siit.spring.domain.entity.Address;
import com.siit.spring.domain.model.AddressDto;
import com.siit.spring.exception.AddressNotFoundException;
import com.siit.spring.mapper.AddressDtoToAddressEntityMapper;
import com.siit.spring.mapper.AddressEntityToAddressDtoMapper;
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

    public AddressDto create(AddressDto addressDto) {
        Address address = addressDtoToAddressEntityMapper.convert(addressDto);
        Address savedAddressEntity = addressRepository.save(address);
        return addressEntityToAddressDtoMapper.convert(savedAddressEntity);
    }

//    public AddressDto findById(long addressId) {
//        return addressRepository.findById(addressId)
//                .map(addressEntityToAddressDtoMapper::convert)
//                .orElseThrow(() -> new SingerNotFoundException("The address with provided id cannot be found!"));
//    }

    public AddressDto findById(long addressId) {
        AddressDto a =  addressRepository.findById(addressId)
                .map((Address address) -> addressEntityToAddressDtoMapper.convert(address))
                .orElseThrow(() -> new AddressNotFoundException("The address with provided id cannot be found!"));

        return a;
    }


    public List<AddressDto> getAll() {
        return addressRepository.getAll()
                .stream()
                .map(addressEntityToAddressDtoMapper::convert)
                .collect(Collectors.toList());
    }

    public AddressDto update(AddressDto addressDto) {
        Address address = addressDtoToAddressEntityMapper.convert(addressDto);
        address.setCity(addressDto.getCity());
        validateAddress(addressDto, address);
        Address savedEntity = addressRepository.save(address);

        return addressEntityToAddressDtoMapper.convert(savedEntity);
    }

    private void validateAddress(AddressDto addressDto, Address address) {
        ModelMapper modelMapper = new ModelMapper();
//        Address addressDtoToAddressEntity = modelMapper.map(addressDto, Address.class);

        if (addressDto.getAddressName() != null) {
            address.setAddressName(addressDto.getAddressName());
        }
        if (addressDto.getCity() != null) {
            address.setCity(addressDto.getCity());
        }
//        if (addressDto.getCustomer() != null) {
//            address.setCustomer(addressDtoToAddressEntity.getCustomer());
//        }
        if (addressDto.getFirstName() != null) {
            address.setFirstName(addressDto.getFirstName());
        }
        if (addressDto.getLastName() != null) {
            address.setLastName(addressDto.getLastName());
        }
        if (addressDto.getPostalCode() != null) {
            address.setPostalCode(addressDto.getPostalCode());
        }

        if (addressDto.getStatus() != null) {
            address.setStatus(addressDto.getStatus());
        }

        if (addressDto.getTelephone() != null) {
            address.setTelephone(addressDto.getTelephone());
        }
    }

    public void delete(Long id) {
        Address existingEntity = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("The address with provided id cannot be found!"));

        addressRepository.delete(existingEntity);
    }

    @Transactional //
    public void updateTransactional(AddressDto addressDto) {
        Address existingEntity = addressRepository.findById(addressDto.getAddressId())
                .orElseThrow(() -> new AddressNotFoundException("The singer with id provided cannot be found"));

        updateFields(existingEntity, addressDto);
    }

    private void updateFields(Address existingEntity, AddressDto addressDto) {
        ModelMapper modelMapper = new ModelMapper();
        Address addressDtoToAddressEntity = modelMapper.map(addressDto, Address.class);

        existingEntity.setTelephone(addressDto.getTelephone());
        existingEntity.setFirstName(addressDto.getFirstName());
        existingEntity.setLastName(addressDto.getLastName());
        existingEntity.setStatus(addressDto.getStatus());
        existingEntity.setPostalCode(addressDto.getPostalCode());
        existingEntity.setCity(addressDto.getCity());
        existingEntity.setAddressId(addressDto.getAddressId());
        existingEntity.setCustomer(addressDtoToAddressEntity.getCustomer());
        existingEntity.setAddressName(addressDto.getAddressName());
    }
}
