package com.toaderAlexandru.onlineStore.controller;

import com.toaderAlexandru.onlineStore.domain.model.PaymentDto;
import com.toaderAlexandru.onlineStore.exception.PaymentNotFoundException;
import com.toaderAlexandru.onlineStore.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentDto create(@RequestBody PaymentDto paymentDto) {
        return paymentService.create(paymentDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PaymentDto getPaymentById(@PathVariable("id") long id) {
        return paymentService.findById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentDto> getAllPayments() {
        return paymentService.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") long id, @RequestBody PaymentDto paymentDto) {
        paymentDto.setId(id);
        paymentService.update(paymentDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        paymentService.delete(id);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public void notFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
