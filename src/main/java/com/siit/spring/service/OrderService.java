package com.siit.spring.service;

import com.siit.spring.domain.entity.*;
import com.siit.spring.domain.model.OrderDto;
import com.siit.spring.exception.OrderCannotBeModifiedException;
import com.siit.spring.exception.OrderNotFoundException;
import com.siit.spring.mapper.OrderDtoOnlyToOrderEntityMapper;
import com.siit.spring.mapper.OrderEntityListOnlyToOrderListDtoMapper;
import com.siit.spring.mapper.OrderEntityOnlyToOrderDtoMapper;
import com.siit.spring.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    public static int ORDER_STATUS_ID = 3;

    private final OrderRepository orderRepository;
    private final OrderEntityListOnlyToOrderListDtoMapper orderEntityListOnlyToOrderListDtoMapper;
    private final OrderEntityOnlyToOrderDtoMapper orderEntityOnlyToOrderDtoMapper;
    private final OrderDtoOnlyToOrderEntityMapper orderDtoOnlyToOrderEntityMapper;
    private final AddressRepository addressRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;

    public OrderDto create(OrderDto orderDto) {
        if (null == orderDto.getAddressId()) {
            throw new NullPointerException("Address id cannot be null!");
        }

        if (null == orderDto.getPaymentId()) {
            throw new NullPointerException("Payment id cannot be null!");
        }

        Order order = orderDtoOnlyToOrderEntityMapper.convert(orderDto);

        if (null == order) {
            throw new NullPointerException("Order cannot be null!");
        }

        orderRepository.save(order);
        return orderEntityListOnlyToOrderListDtoMapper.convert(order);
    }

    public OrderDto findById(long orderId) {
        return orderRepository.findById(orderId)
                .map(orderEntityListOnlyToOrderListDtoMapper::convert)
                .orElseThrow(() -> new OrderNotFoundException("The order with provided id cannot be found!"));
    }

    public List<OrderDto> getAll() {
        return orderRepository.getAll()
                .stream()
                .map(orderEntityOnlyToOrderDtoMapper::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(OrderDto orderDto) {
        Order order = orderRepository.findById(orderDto.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException("Order with provided id cannot be found!"));

        updateFields(order, orderDto);
    }

    private void updateFields(Order order, OrderDto orderDto) {
        if (order.getOrderStatus().getId() >= ORDER_STATUS_ID) {
            throw new OrderCannotBeModifiedException("Order cannot be modified on this status: " + order.getOrderStatus().getId() + "!");
        } else {
            if (null != orderDto.getAddressId()) {
                addressRepository.findById(orderDto.getAddressId()).ifPresent(order::setAddress);
            }

            if (null != orderDto.getOrderStatusId()) {
                orderStatusRepository.findById(orderDto.getOrderStatusId()).ifPresent(order::setOrderStatus);
            }

            if (null != orderDto.getPaymentId()) {
                paymentRepository.findById(orderDto.getPaymentId()).ifPresent(order::setPayment);
            }

            if (null != orderDto.getCurrency()) {
                order.setCurrency(orderDto.getCurrency());
            }

            if (null != orderDto.getCustomerId()) {
                customerRepository.findById(orderDto.getCustomerId()).ifPresent(order::setCustomer);
            }

            if (null != orderDto.getStatus()) {
                order.setStatus(orderDto.getStatus());
            }

            if (null != orderDto.getTotal()) {
                order.setTotal(orderDto.getTotal());
            }
        }
    }

    public void delete(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("The order with provided id cannot be found!"));

        orderRepository.delete(order);
    }
}
