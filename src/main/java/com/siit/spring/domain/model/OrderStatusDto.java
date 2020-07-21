package com.siit.spring.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class OrderStatusDto {
    private Long id;

    private String name;

    private Integer sortOrder;

    @Nullable
    private Long orderId;
}
