package com.siit.spring.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDto {
    private String name;

    private Integer status;

    private OrderDto order;
}
