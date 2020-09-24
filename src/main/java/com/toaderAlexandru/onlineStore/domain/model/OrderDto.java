package com.toaderAlexandru.onlineStore.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;

@Data
@Builder
public class OrderDto {
    private Long orderId;

    private Long customerId;

    @Nullable
    private Long invoiceId;

    private Long addressId;

    private Long paymentId;

    private BigDecimal total;

    private String currency;

    private Long orderStatusId;

    private Integer status;
}
