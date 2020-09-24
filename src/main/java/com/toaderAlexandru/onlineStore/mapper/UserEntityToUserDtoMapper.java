package com.toaderAlexandru.onlineStore.mapper;

import com.toaderAlexandru.onlineStore.domain.entity.User;
import com.toaderAlexandru.onlineStore.domain.model.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserEntityToUserDtoMapper implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User source) {
        return UserDto.builder()
                .id(source.getId())
                .email(source.getEmail())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .username(source.getUsername())
                .password(source.getPassword())
                .status(source.getStatus())
                .userRoleId(source.getUserRole().getId())
                .build();
    }
}
