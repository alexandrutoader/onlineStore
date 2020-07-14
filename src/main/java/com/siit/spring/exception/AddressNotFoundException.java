package com.siit.spring.exception;

import org.springframework.core.NestedRuntimeException;

public class AddressNotFoundException extends NestedRuntimeException {
    public AddressNotFoundException(String msg) {
        super(msg);
    }
}
