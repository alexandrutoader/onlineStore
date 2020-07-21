package com.siit.spring.controller;

import com.siit.spring.domain.model.UserRoleDto;
import com.siit.spring.exception.UserRoleNotFoundException;
import com.siit.spring.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/userRole")
@AllArgsConstructor
public class UserRoleController {
    private final UserRoleService userRoleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserRoleDto create(@RequestBody UserRoleDto userRoleDto) {
        return userRoleService.create(userRoleDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserRoleDto getUserRoleById(@PathVariable("id") long id) {
        return userRoleService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserRoleDto> getAllUserRoles() {
        return userRoleService.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") long id, @RequestBody UserRoleDto userRoleDto) {
        userRoleDto.setId(id);
        userRoleService.update(userRoleDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        userRoleService.delete(id);
    }

    @ExceptionHandler(UserRoleNotFoundException.class)
    public void notFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
