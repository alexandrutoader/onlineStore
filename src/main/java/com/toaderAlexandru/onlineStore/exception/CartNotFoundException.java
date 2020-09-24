package com.toaderAlexandru.onlineStore.exception;

import org.springframework.core.NestedRuntimeException;

public class CartNotFoundException  extends NestedRuntimeException {
    public CartNotFoundException(String msg) {
        super(msg);
    }
}
