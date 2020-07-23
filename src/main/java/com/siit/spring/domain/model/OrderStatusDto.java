package com.siit.spring.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderStatusDto {
    private Long id;

    private String name;

    private Integer sortOrder;
}
