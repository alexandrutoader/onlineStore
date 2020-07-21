package com.siit.spring.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvoiceDto {
    private Long orderId;

    private Long invoiceNumber;

    private Integer type;

    private Integer status;
}
