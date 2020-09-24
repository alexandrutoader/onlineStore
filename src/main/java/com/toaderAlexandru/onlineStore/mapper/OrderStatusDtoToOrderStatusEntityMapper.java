package com.toaderAlexandru.onlineStore.mapper;

import com.toaderAlexandru.onlineStore.domain.entity.OrderStatus;
import com.toaderAlexandru.onlineStore.domain.model.OrderStatusDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class OrderStatusDtoToOrderStatusEntityMapper implements Converter<OrderStatusDto, OrderStatus> {
    @Override
    public OrderStatus convert(OrderStatusDto source) {
        return OrderStatus.builder()
                .id(source.getId())
                .sortOrder(source.getSortOrder())
                .name(source.getName())
                .dateAdded(LocalDateTime.now())
                .dateModified(LocalDateTime.now())
                .build();
    }
}
