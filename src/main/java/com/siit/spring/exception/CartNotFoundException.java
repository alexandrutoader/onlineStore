package com.siit.spring.exception;

import org.springframework.core.NestedRuntimeException;

public class CartNotFoundException  extends NestedRuntimeException {
    public CartNotFoundException(String msg) {
        super(msg);
    }
}
