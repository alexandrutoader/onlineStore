package com.toaderAlexandru.onlineStore.mapper;

import com.toaderAlexandru.onlineStore.domain.entity.Order;
import com.toaderAlexandru.onlineStore.domain.model.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderEntityOnlyToOrderDtoMapper implements Converter<Order, OrderDto> {
    @Override
    public OrderDto convert(Order source) {
        Long invoiceId = null;
        if (null != source.getInvoices() && source.getInvoices().size() > 0) {
            invoiceId = source.getInvoices().get(0).getInvoiceId();
        }

        return OrderDto.builder()
                .orderId(source.getOrderId())
                .addressId(source.getAddress().getAddressId())
                .currency(source.getCurrency())
                .customerId(source.getCustomer().getCustomerId())
                .invoiceId(invoiceId)
                .paymentId(source.getPayment().getId())
                .orderStatusId(source.getOrderStatus().getId())
                .status(source.getStatus())
                .total(source.getTotal())
                .build();
    }
}