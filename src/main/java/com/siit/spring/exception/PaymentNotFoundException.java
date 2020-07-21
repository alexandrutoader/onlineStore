package com.siit.spring.exception;

import org.springframework.core.NestedRuntimeException;

public class PaymentNotFoundException extends NestedRuntimeException {
    public PaymentNotFoundException(String msg) {
        super(msg);
    }
}
