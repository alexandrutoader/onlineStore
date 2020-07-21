package com.siit.spring.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class AddressDto {
    @Nullable
    private Long addressId;

    @Nullable
    private Long customerId;

    @Nullable
    private String firstName;

    @Nullable
    private String lastName;

    @Nullable
    private String telephone;

    @Nullable
    private String addressName;

    @Nullable
    private String city;

    @Nullable
    private String postalCode;

    @Nullable
    private Integer status;
}
