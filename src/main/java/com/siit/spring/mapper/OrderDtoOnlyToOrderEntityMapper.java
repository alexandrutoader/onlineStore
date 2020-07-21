package com.siit.spring.mapper;

import com.siit.spring.domain.entity.*;
import com.siit.spring.domain.model.*;
import com.siit.spring.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class OrderDtoOnlyToOrderEntityMapper implements Converter<OrderDto, Order> {
    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public Order convert(OrderDto source) {
        Address address = addressRepository.findById(source.getAddressId()).orElse(null);
        Customer customer = customerRepository.findById(source.getCustomerId()).orElse(null);
        OrderStatus orderStatus = orderStatusRepository.findById(source.getOrderStatusId()).orElse(null);
        Payment payment = paymentRepository.findById(source.getPaymentId()).orElse(null);

        return Order.builder()
                .invoiceId(source.getInvoiceId())
                .address(address)
                .currency(source.getCurrency())
                .customer(customer)
                .payment(payment)
                .orderStatus(orderStatus)
                .status(source.getStatus())
                .total(source.getTotal())
                .dateAdded(LocalDate.now())
                .dateModified(LocalDate.now())
                .build();
    }
}
