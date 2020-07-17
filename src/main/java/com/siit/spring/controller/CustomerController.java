package com.siit.spring.controller;

import com.siit.spring.domain.model.CustomerDto;
import com.siit.spring.exception.CustomerNotFoundException;
import com.siit.spring.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto create(@RequestBody CustomerDto customerDto) {
        return customerService.create(customerDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto getCustomerById(@PathVariable("id") long id) {
        return customerService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") long id, @RequestBody CustomerDto customerDto) {
        customerDto.setCustomerId(id);
        customerService.update(customerDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        customerService.delete(id);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public void notFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
