package com.siit.spring.exception;

import org.springframework.core.NestedRuntimeException;

public class UserNotFoundException extends NestedRuntimeException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
