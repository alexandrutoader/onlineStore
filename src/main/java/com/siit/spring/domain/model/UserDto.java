package com.siit.spring.domain.model;

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

    private Long userRoleId;

    private Integer status;
}
