package com.siit.spring.exception;

import org.springframework.core.NestedRuntimeException;

public class OrderCannotBeModifiedException extends NestedRuntimeException {
    public OrderCannotBeModifiedException(String msg) {
        super(msg);
    }
}
