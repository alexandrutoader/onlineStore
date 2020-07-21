package com.siit.spring.exception;

import org.springframework.core.NestedRuntimeException;

public class UserRoleNotFoundException extends NestedRuntimeException {
    public UserRoleNotFoundException(String msg) {
        super(msg);
    }
}
