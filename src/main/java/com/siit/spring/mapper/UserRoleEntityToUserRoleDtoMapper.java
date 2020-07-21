package com.siit.spring.mapper;

import com.siit.spring.domain.entity.UserRole;
import com.siit.spring.domain.model.UserRoleDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRoleEntityToUserRoleDtoMapper implements Converter<UserRole, UserRoleDto> {
    @Override
    public UserRoleDto convert(UserRole source) {
        return UserRoleDto.builder()
                .role(source.getRole())
                .status(source.getStatus())
                .userId(source.getUser().getId())
                .build();
    }
}
