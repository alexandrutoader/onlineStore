package com.siit.spring.service;

import com.siit.spring.domain.entity.OrderStatus;
import com.siit.spring.domain.entity.Payment;
import com.siit.spring.domain.model.OrderStatusDto;
import com.siit.spring.domain.model.PaymentDto;
import com.siit.spring.exception.OrderStatusNotFoundException;
import com.siit.spring.exception.PaymentNotFoundException;
import com.siit.spring.mapper.OrderStatusDtoToOrderStatusEntityMapper;
import com.siit.spring.mapper.OrderStatusEntityToOrderStatusDtoMapper;
import com.siit.spring.repository.OrderStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;
    private final OrderStatusDtoToOrderStatusEntityMapper orderStatusDtoToOrderStatusEntityMapper;
    private final OrderStatusEntityToOrderStatusDtoMapper orderStatusEntityToOrderStatusDtoMapper;

    public OrderStatusDto create(OrderStatusDto orderStatusDto) {
        OrderStatus orderStatus = orderStatusDtoToOrderStatusEntityMapper.convert(orderStatusDto);

        if (null == orderStatus) {
            throw new NullPointerException("Order status object cannot be null!");
        }

        orderStatusRepository.save(orderStatus);
        return orderStatusEntityToOrderStatusDtoMapper.convert(orderStatus);
    }

    public OrderStatusDto findById(long orderStatusId) {
        return orderStatusRepository.findById(orderStatusId)
                .map(orderStatusEntityToOrderStatusDtoMapper::convert)
                .orElseThrow(() -> new OrderStatusNotFoundException("The order status with provided id cannot be found!"));
    }

    public List<OrderStatusDto> getAll() {
        return orderStatusRepository.getAll()
                .stream()
                .map(orderStatusEntityToOrderStatusDtoMapper::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(OrderStatusDto orderStatusDto) {
        OrderStatus orderStatus = orderStatusRepository.findById(orderStatusDto.getId())
                .orElseThrow(() -> new OrderStatusNotFoundException("Order status with provided id cannot be found!"));

        updateFields(orderStatus, orderStatusDto);
    }

    private void updateFields(OrderStatus orderStatus, OrderStatusDto orderStatusDto) {
        if (null != orderStatusDto.getName()) {
            orderStatus.setName(orderStatusDto.getName());
        }

        if (null != orderStatusDto.getSortOrder()) {
            orderStatus.setSortOrder(orderStatusDto.getSortOrder());
        }

        orderStatus.setDateModified(LocalDateTime.now());
    }

    public void delete(Long id) {
        OrderStatus orderStatus = orderStatusRepository.findById(id)
                .orElseThrow(() -> new OrderStatusNotFoundException("The order status with provided id cannot be found!"));

        orderStatusRepository.delete(orderStatus);
    }
}
