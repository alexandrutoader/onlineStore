package com.toaderAlexandru.onlineStore.mapper;

import com.toaderAlexandru.onlineStore.domain.entity.Invoice;
import com.toaderAlexandru.onlineStore.domain.model.InvoiceDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InvoiceEntityToInvoiceDtoMapper implements Converter<Invoice, InvoiceDto> {
    @Override
    public InvoiceDto convert(Invoice source) {
        Long orderId = null;

        if (null != source.getOrder()) {
            orderId = source.getOrder().getOrderId();
        }
        return InvoiceDto.builder()
                .invoiceId(source.getInvoiceId())
                .invoiceNumber(source.getInvoiceNumber())
                .orderId(orderId)
                .status(source.getStatus())
                .type(source.getType())
                .dateAdded(source.getDateAdded())
                .dateModified(source.getDateModified())
                .build();
    }
}
