package com.siit.spring.exception;

import org.springframework.core.NestedRuntimeException;

public class OrderNotFoundException extends NestedRuntimeException {
    public OrderNotFoundException(String msg) {
        super(msg);
    }
}
