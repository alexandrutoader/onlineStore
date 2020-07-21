package com.siit.spring.mapper;

import com.siit.spring.domain.entity.OrderStatus;
import com.siit.spring.domain.model.OrderStatusDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderStatusEntityToOrderStatusDtoMapper implements Converter<OrderStatus, OrderStatusDto> {
    @Override
    public OrderStatusDto convert(OrderStatus source) {
        return OrderStatusDto.builder()
                .id(source.getId())
                .sortOrder(source.getSortOrder())
                .name(source.getName())
                .build();
    }
}
