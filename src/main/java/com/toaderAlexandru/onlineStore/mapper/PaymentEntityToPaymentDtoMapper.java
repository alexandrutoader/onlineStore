package com.toaderAlexandru.onlineStore.mapper;

import com.toaderAlexandru.onlineStore.domain.entity.Payment;
import com.toaderAlexandru.onlineStore.domain.model.PaymentDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentEntityToPaymentDtoMapper implements Converter<Payment, PaymentDto> {
    @Override
    public PaymentDto convert(Payment source) {
        return PaymentDto.builder()
                .id(source.getId())
                .status(source.getStatus())
                .name(source.getName())
                .build();
    }
}
