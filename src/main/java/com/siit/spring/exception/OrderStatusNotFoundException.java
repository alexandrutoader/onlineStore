package com.siit.spring.exception;

import org.springframework.core.NestedRuntimeException;

public class OrderStatusNotFoundException extends NestedRuntimeException {
    public OrderStatusNotFoundException(String msg) {
        super(msg);
    }
}
