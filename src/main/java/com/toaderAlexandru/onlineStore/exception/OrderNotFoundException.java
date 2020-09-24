package com.toaderAlexandru.onlineStore.exception;

import org.springframework.core.NestedRuntimeException;

public class OrderNotFoundException extends NestedRuntimeException {
    public OrderNotFoundException(String msg) {
        super(msg);
    }
}
