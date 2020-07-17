package com.siit.spring.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@Builder
public class CustomerDto {
    private Long customerId;

    private String firstName;

    private String lastName;

    private String email;

    private String telephone;

    private String password;

    private Long addressId;

    private Long agentId;

    private Integer status;

//    private List<AddressDto> addresses;

//    @Nullable
//    private CartDto cart;
//
//    private List<OrderDto> orders;
}