package com.siit.spring.controller;

import com.siit.spring.domain.model.InvoiceDto;
import com.siit.spring.domain.model.OrderDto;
import com.siit.spring.exception.InvoiceNotFoundException;
import com.siit.spring.exception.OrderNotFoundException;
import com.siit.spring.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/invoice")
@AllArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceDto create(@RequestBody InvoiceDto invoiceDto) {
        return invoiceService.create(invoiceDto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceDto getInvoiceById(@PathVariable("id") long id) {
        return invoiceService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceDto> getAllInvoices() {
        return invoiceService.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") long id, @RequestBody InvoiceDto invoiceDto) {
        invoiceDto.setInvoiceId(id);
        invoiceService.update(invoiceDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        invoiceService.delete(id);
    }

    @ExceptionHandler(InvoiceNotFoundException.class)
    public void notFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }
}
