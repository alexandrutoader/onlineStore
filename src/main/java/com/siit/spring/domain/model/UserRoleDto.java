package com.siit.spring.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRoleDto {
    private String role;

    private Integer status;

    private Long userId;
}
