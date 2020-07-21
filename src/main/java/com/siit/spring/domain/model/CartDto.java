package com.siit.spring.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class CartDto {
    @Nullable
    private Long customerId;

    @Nullable
    private String token;

    @Nullable
    private Integer status;
}
