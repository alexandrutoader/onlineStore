package com.siit.spring.domain.model;

import com.siit.spring.domain.entity.Order;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderStatusDto {
    private String name;

    private Integer sortOrder;

    private Order order;
}
