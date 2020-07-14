package com.siit.spring.domain.model;

import com.siit.spring.domain.entity.*;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderDto {
    private Customer customer;

    private List<Invoice> invoices;

    private Address address;

    private Payment payment;

    private BigDecimal total;

    private String currency;

    private OrderStatus orderStatus;

    private Integer status;
}
