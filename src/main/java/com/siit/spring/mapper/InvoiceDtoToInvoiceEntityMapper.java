package com.siit.spring.mapper;

import com.siit.spring.domain.entity.Invoice;
import com.siit.spring.domain.entity.Order;
import com.siit.spring.domain.model.InvoiceDto;
import com.siit.spring.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class InvoiceDtoToInvoiceEntityMapper implements Converter<InvoiceDto, Invoice> {
    private final OrderRepository orderRepository;

    @Override
    public Invoice convert(InvoiceDto source) {
        Order order = orderRepository.findById(source.getOrderId()).orElse(null);

        if (null == source.getDateAdded()) {
            source.setDateAdded(LocalDateTime.now());
        }

        if (null == source.getDateModified()) {
            source.setDateModified(LocalDateTime.now());
        }

        return Invoice.builder()
                .invoiceNumber(source.getInvoiceNumber())
                .order(order)
                .status(source.getStatus())
                .type(source.getType())
                .dateAdded(source.getDateAdded())
                .dateModified(source.getDateModified())
                .build();
    }
}
