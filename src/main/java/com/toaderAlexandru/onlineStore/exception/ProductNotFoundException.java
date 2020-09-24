package com.toaderAlexandru.onlineStore.exception;

import org.springframework.core.NestedRuntimeException;

public class ProductNotFoundException extends NestedRuntimeException {
    public ProductNotFoundException(String msg) {
        super(msg);
    }
}
