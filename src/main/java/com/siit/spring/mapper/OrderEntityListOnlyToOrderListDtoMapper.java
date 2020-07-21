package com.siit.spring.mapper;

import com.siit.spring.domain.entity.*;
import com.siit.spring.domain.model.*;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderEntityListOnlyToOrderListDtoMapper implements Converter<Order, OrderDto> {

    @Override
    public OrderDto convert(Order source) {
        Long invoiceId = null;
        if (null != source.getInvoices()) {
            invoiceId = source.getInvoices().get(0).getInvoiceId();
        }
        return OrderDto.builder()
                .orderId(source.getOrderId())
                .addressId(source.getAddress().getAddressId())
                .currency(source.getCurrency())
                .customerId(source.getCustomer().getCustomerId())
                .invoiceId(invoiceId)
                .orderStatusId(source.getOrderStatus().getId())
                .paymentId(source.getPayment().getId())
                .status(source.getStatus())
                .total(source.getTotal())
                .build();
    }
}
