package com.toaderAlexandru.onlineStore.mapper;

import com.toaderAlexandru.onlineStore.domain.entity.Payment;
import com.toaderAlexandru.onlineStore.domain.model.PaymentDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class PaymentDtoToPaymentEntityMapper implements Converter<PaymentDto, Payment> {
    @Override
    public Payment convert(PaymentDto source) {
        return Payment.builder()
                .id(source.getId())
                .status(source.getStatus())
                .name(source.getName())
                .dateAdded(LocalDateTime.now())
                .build();
    }
}
