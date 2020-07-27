package com.siit.spring.service;

import com.siit.spring.domain.entity.User;
import com.siit.spring.domain.model.UserDto;
import com.siit.spring.exception.UserNotFoundException;
import com.siit.spring.mapper.UserDtoToUserEntityMapper;
import com.siit.spring.mapper.UserEntityToUserDtoMapper;
import com.siit.spring.repository.UserRepository;
import com.siit.spring.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserDtoToUserEntityMapper userDtoToUserEntityMapper;
    private final UserEntityToUserDtoMapper userEntityToUserDtoMapper;

    public UserDto create(UserDto userDto) {
        User user = userDtoToUserEntityMapper.convert(userDto);

        if (null == user) {
            throw new NullPointerException("User cannot be null!");
        }

        user = userRepository.save(user);
        return userEntityToUserDtoMapper.convert(user);
    }

    public UserDto findById(long userId) {
        return userRepository.findById(userId)
                .map(userEntityToUserDtoMapper::convert)
                .orElseThrow(() -> new UserNotFoundException("The user with provided id cannot be found!"));
    }

    public List<UserDto> getAll() {
        return userRepository.getAll()
                .stream()
                .map(userEntityToUserDtoMapper::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new UserNotFoundException("User with provided id cannot be found!"));

        updateFields(user, userDto);
    }

    private void updateFields(User user, UserDto userDto) {
        if (null != userDto.getEmail()) {
            user.setEmail(userDto.getEmail());
        }

        if (null != userDto.getFirstName()) {
            user.setFirstName(userDto.getFirstName());
        }

        if (null != userDto.getLastName()) {
            user.setLastName(userDto.getLastName());
        }

        if (null != userDto.getStatus()) {
            user.setStatus(userDto.getStatus());
        }

        if (null != userDto.getUsername()) {
            user.setUsername(userDto.getUsername());
        }

        if (null != userDto.getPassword()) {
            int strength = 10;
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength);
            String encodedPassword = bCryptPasswordEncoder.encode(userDto.getPassword());
            user.setPassword(encodedPassword);
        }

        if (null != userDto.getUserRoleId()) {
            userRoleRepository.findById(userDto.getUserRoleId()).ifPresent(user::setUserRole);
        }

        user.setDateModified(LocalDateTime.now());
    }

    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("The user with provided id cannot be found!"));

        userRepository.delete(user);
    }
}
