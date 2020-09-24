package com.toaderAlexandru.onlineStore.exception;

import org.springframework.core.NestedRuntimeException;

public class UserNotFoundException extends NestedRuntimeException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
