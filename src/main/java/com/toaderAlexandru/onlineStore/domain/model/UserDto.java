package com.toaderAlexandru.onlineStore.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Long userRoleId;

    private Integer status;
}
