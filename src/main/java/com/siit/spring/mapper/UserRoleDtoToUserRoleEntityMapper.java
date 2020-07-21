package com.siit.spring.mapper;

import com.siit.spring.domain.entity.User;
import com.siit.spring.domain.entity.UserRole;
import com.siit.spring.domain.model.UserRoleDto;
import com.siit.spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRoleDtoToUserRoleEntityMapper implements Converter<UserRoleDto, UserRole> {
    private final UserRepository userRepository;

    @Override
    public UserRole convert(UserRoleDto source) {
        User user = userRepository.findById(source.getUserId()).orElse(null);

        return UserRole.builder()
                .role(source.getRole())
                .status(source.getStatus())
                .user(user)
                .build();
    }
}
