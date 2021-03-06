package com.toaderAlexandru.onlineStore.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentDto {
    private Long id;

    private String name;

    private Integer status;
}
