package com.toaderAlexandru.onlineStore.mapper;

import com.toaderAlexandru.onlineStore.domain.entity.User;
import com.toaderAlexandru.onlineStore.domain.entity.UserRole;
import com.toaderAlexandru.onlineStore.domain.model.UserDto;
import com.toaderAlexandru.onlineStore.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDtoToUserEntityMapper implements Converter<UserDto, User> {
    private final UserRoleRepository userRoleRepository;

    @Override
    public User convert(UserDto source) {
        UserRole userRole = userRoleRepository.findById(source.getUserRoleId()).orElse(null);

        return User.builder()
                .id(source.getId())
                .email(source.getEmail())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .username(source.getUsername())
                .password(source.getPassword())
                .status(source.getStatus())
                .userRole(userRole)
                .build();
    }
}
