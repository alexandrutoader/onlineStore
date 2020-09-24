package com.toaderAlexandru.onlineStore.exception;

import org.springframework.core.NestedRuntimeException;

public class PaymentNotFoundException extends NestedRuntimeException {
    public PaymentNotFoundException(String msg) {
        super(msg);
    }
}
