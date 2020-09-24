package com.toaderAlexandru.onlineStore.service;

import com.toaderAlexandru.onlineStore.domain.entity.UserRole;
import com.toaderAlexandru.onlineStore.domain.model.UserRoleDto;
import com.toaderAlexandru.onlineStore.exception.UserRoleNotFoundException;
import com.toaderAlexandru.onlineStore.mapper.UserRoleDtoToUserRoleEntityMapper;
import com.toaderAlexandru.onlineStore.mapper.UserRoleEntityToUserRoleDtoMapper;
import com.toaderAlexandru.onlineStore.repository.UserRepository;
import com.toaderAlexandru.onlineStore.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final UserRoleDtoToUserRoleEntityMapper userRoleDtoToUserRoleEntityMapper;
    private final UserRoleEntityToUserRoleDtoMapper userRoleEntityToUserRoleDtoMapper;

    public UserRoleDto create(UserRoleDto userRoleDto) {
        UserRole userRole = userRoleDtoToUserRoleEntityMapper.convert(userRoleDto);

        if (null == userRole) {
            throw new NullPointerException("User role object cannot be null!");
        }

        userRole = userRoleRepository.save(userRole);
        return userRoleEntityToUserRoleDtoMapper.convert(userRole);
    }

    public UserRoleDto findById(long userRoleId) {
        return userRoleRepository.findById(userRoleId)
                .map(userRoleEntityToUserRoleDtoMapper::convert)
                .orElseThrow(() -> new UserRoleNotFoundException("The user role with provided id cannot be found!"));
    }

    public List<UserRoleDto> getAll() {
        return userRoleRepository.getAll()
                .stream()
                .map(userRoleEntityToUserRoleDtoMapper::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(UserRoleDto userRoleDto) {
        UserRole userRole = userRoleRepository.findById(userRoleDto.getId())
                .orElseThrow(() -> new UserRoleNotFoundException("User role with provided id cannot be found!"));

        updateFields(userRole, userRoleDto);
    }

    private void updateFields(UserRole userRole, UserRoleDto userRoleDto) {
        if (null != userRoleDto.getRole()) {
            userRole.setRole(userRoleDto.getRole());
        }

        if (null != userRoleDto.getStatus()) {
            userRole.setStatus(userRoleDto.getStatus());
        }
    }

    public void delete(Long id) {
        UserRole userRole = userRoleRepository.findById(id)
                .orElseThrow(() -> new UserRoleNotFoundException("The user role with provided id cannot be found!"));

        userRoleRepository.delete(userRole);
    }
}
