package com.siit.spring.mapper;

import com.siit.spring.domain.entity.UserRole;
import com.siit.spring.domain.model.UserRoleDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class UserRoleDtoToUserRoleEntityMapper implements Converter<UserRoleDto, UserRole> {

    @Override
    public UserRole convert(UserRoleDto source) {

        return UserRole.builder()
                .role(source.getRole())
                .status(source.getStatus())
                .dateAdded(LocalDateTime.now())
                .build();
    }
}
