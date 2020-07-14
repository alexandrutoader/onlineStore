package com.siit.spring.domain.model;

import com.siit.spring.domain.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRoleDto {
    private String role;

    private Integer status;

    private User user;
}
