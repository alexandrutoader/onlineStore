package com.siit.spring.exception;

import org.springframework.core.NestedRuntimeException;

public class InvoiceNotFoundException extends NestedRuntimeException {
    public InvoiceNotFoundException(String msg) {
        super(msg);
    }
}
