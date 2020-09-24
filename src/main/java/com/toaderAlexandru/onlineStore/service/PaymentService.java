package com.toaderAlexandru.onlineStore.service;

import com.toaderAlexandru.onlineStore.domain.entity.Payment;
import com.toaderAlexandru.onlineStore.domain.model.PaymentDto;
import com.toaderAlexandru.onlineStore.exception.PaymentNotFoundException;
import com.toaderAlexandru.onlineStore.mapper.PaymentDtoToPaymentEntityMapper;
import com.toaderAlexandru.onlineStore.mapper.PaymentEntityToPaymentDtoMapper;
import com.toaderAlexandru.onlineStore.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentDtoToPaymentEntityMapper paymentDtoToPaymentEntityMapper;
    private final PaymentEntityToPaymentDtoMapper paymentEntityToPaymentDtoMapper;

    public PaymentDto create(PaymentDto paymentDto) {
        Payment payment = paymentDtoToPaymentEntityMapper.convert(paymentDto);

        if (null == payment) {
            throw new NullPointerException("Payment cannot be null!");
        }

        payment = paymentRepository.save(payment);
        return paymentEntityToPaymentDtoMapper.convert(payment);
    }

    public PaymentDto findById(long paymentId) {
        return paymentRepository.findById(paymentId)
                .map(paymentEntityToPaymentDtoMapper::convert)
                .orElseThrow(() -> new PaymentNotFoundException("The payment with provided id cannot be found!"));
    }

    public List<PaymentDto> getAll() {
        return paymentRepository.getAll()
                .stream()
                .map(paymentEntityToPaymentDtoMapper::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(PaymentDto paymentDto) {
        Payment payment = paymentRepository.findById(paymentDto.getId())
                .orElseThrow(() -> new PaymentNotFoundException("Invoice with provided id cannot be found!"));

        updateFields(payment, paymentDto);
    }

    private void updateFields(Payment payment, PaymentDto paymentDto) {
        if (null != paymentDto.getName()) {
            payment.setName(paymentDto.getName());
        }

        if (null != paymentDto.getStatus()) {
            payment.setStatus(paymentDto.getStatus());
        }
    }

    public void delete(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("The payment with provided id cannot be found!"));

        paymentRepository.delete(payment);
    }
}
