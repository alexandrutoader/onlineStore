package com.siit.spring.domain.model;

import com.siit.spring.domain.entity.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private UserRole userRole;

    private Integer status;
}
