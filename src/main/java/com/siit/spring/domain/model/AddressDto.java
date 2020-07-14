package com.siit.spring.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {
    private Long addressId;

    private Integer customerId;
//    private CustomerDto customer;

    private String firstName;

    private String lastName;

    private String telephone;

    private String addressName;

    private String city;

    private String postalCode;

    private Integer status;
}
