package com.toaderAlexandru.onlineStore.exception;

import org.springframework.core.NestedRuntimeException;

public class CustomerNotFoundException extends NestedRuntimeException {
    public CustomerNotFoundException(String msg) {
        super(msg);
    }
}
