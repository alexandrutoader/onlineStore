package com.toaderAlexandru.onlineStore.mapper;

import com.toaderAlexandru.onlineStore.domain.entity.UserRole;
import com.toaderAlexandru.onlineStore.domain.model.UserRoleDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRoleEntityToUserRoleDtoMapper implements Converter<UserRole, UserRoleDto> {
    @Override
    public UserRoleDto convert(UserRole source) {
        return UserRoleDto.builder()
                .id(source.getId())
                .role(source.getRole())
                .status(source.getStatus())
                .build();
    }
}
