package com.toaderAlexandru.onlineStore.address;

import com.toaderAlexandru.onlineStore.domain.entity.Address;
import com.toaderAlexandru.onlineStore.domain.entity.Customer;
import com.toaderAlexandru.onlineStore.domain.model.AddressDto;
import com.toaderAlexandru.onlineStore.mapper.AddressDtoToAddressEntityMapper;
import com.toaderAlexandru.onlineStore.mapper.AddressEntityToAddressDtoMapper;
import com.toaderAlexandru.onlineStore.repository.AddressRepository;
import com.toaderAlexandru.onlineStore.service.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddressTests {
    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressDtoToAddressEntityMapper addressDtoToAddressEntityMapper;

    @Mock
    private AddressEntityToAddressDtoMapper addressEntityToAddressDtoMapper;

    @InjectMocks
    private AddressService sut;

    public Address getAddressForTest() {
        return Address.builder()
                .addressId((long) 1)
                .customer(new Customer())
                .addressName("Str Test")
                .firstName("Alex")
                .lastName("Popescu")
                .telephone("0731458921")
                .postalCode("1234")
                .city("Iasi")
                .status(1)
                .build();
    }

    public AddressDto getAddressDtoForTest() {
        return AddressDto.builder()
                .addressId((long) 8)
                .customerId(1L)
                .addressName("Str Test")
                .firstName("Alex")
                .lastName("Popescu")
                .telephone("0731458921")
                .postalCode("1234")
                .city("Iasi")
                .status(1)
                .build();
    }

    @Test
    public void given_address_when_create_new_address_then_saved_address_is_returned() {
        //Given
        AddressDto addressDto = getAddressDtoForTest();

        var addressDtoMock = mock(AddressDto.class);
        var addressEntityMock = mock(Address.class);
        var savedEntityMock = mock(Address.class);

        given(addressDtoToAddressEntityMapper.convert(addressDtoMock)).willReturn(addressEntityMock);
        given(addressRepository.save(addressEntityMock)).willReturn(savedEntityMock);
        given(addressEntityToAddressDtoMapper.convert(savedEntityMock)).willReturn(addressDto);
        given(addressDtoMock.getCustomerId()).willReturn(1L);

        //WHEN
        AddressDto actualAddressDto = sut.create(addressDtoMock);

        //Then
        verify(addressRepository).save(addressEntityMock);
        assertThat(actualAddressDto).isSameAs(addressDto);
    }

    @Test
    public void given_address_when_delete_then_address_is_deleted() {
        // Given
        Address address = getAddressForTest();
        Optional<Address> optionalEntityType = Optional.of(address);
        Mockito.when(addressRepository.findById(1L)).thenReturn(optionalEntityType);

        // When
        sut.delete(address.getAddressId());

        // Then
        Mockito.verify(addressRepository, times(1)).delete(address);
    }

    public void given_address_when_update_then_address_is_updated() {

    }
}
