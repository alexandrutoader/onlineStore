package com.siit.spring.mapper;

import com.siit.spring.domain.entity.Customer;
import com.siit.spring.domain.entity.Order;
import com.siit.spring.domain.model.OrderDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderEntityListOnlyToOrderListDtoMapper implements Converter<Order, OrderDto> {
//    private final OrderEntityOnlyToOrderDtoMapper orderEntityOnlyToOrderDtoMapper;

    @Override
    public OrderDto convert(Order source) {
        return OrderDto.builder()
                .address(source.getAddress())
                .currency(source.getCurrency())
                .customer(source.getCustomer())
                .invoices(source.getInvoices())
                .orderStatus(source.getOrderStatus())
                .payment(source.getPayment())
                .status(source.getStatus())
                .total(source.getTotal())
                .build();
    }
}