package com.toaderAlexandru.onlineStore.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class InvoiceDto {
    private Long invoiceId;

    private Long orderId;

    private Long invoiceNumber;

    private Integer type;

    private Integer status;

    private LocalDateTime dateAdded;

    private LocalDateTime dateModified;
}
