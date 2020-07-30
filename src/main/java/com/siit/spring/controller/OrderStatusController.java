package com.siit.spring.controller;

import com.siit.spring.domain.model.OrderStatusDto;
import com.siit.spring.exception.OrderStatusNotFoundException;
import com.siit.spring.service.OrderStatusService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/orderStatus")
@AllArgsConstructor
public class OrderStatusController {
    private final OrderStatusService orderStatusService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderStatusDto create(@RequestBody OrderStatusDto orderStatusDto) {
        return orderStatusService.create(orderStatusDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderStatusDto getOrderStatusById(@PathVariable("id") long id) {
        return orderStatusService.findById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderStatusDto> getAllOrderStatuses() {
        return orderStatusService.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") long id, @RequestBody OrderStatusDto orderStatusDto) {
        orderStatusDto.setId(id);
        orderStatusService.update(orderStatusDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        orderStatusService.delete(id);
    }

    @ExceptionHandler(OrderStatusNotFoundException.class)
    public void notFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
