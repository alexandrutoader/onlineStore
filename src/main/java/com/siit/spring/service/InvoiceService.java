package com.siit.spring.service;

import com.siit.spring.domain.entity.Invoice;
import com.siit.spring.domain.model.InvoiceDto;
import com.siit.spring.exception.InvoiceNotFoundException;
import com.siit.spring.mapper.InvoiceDtoToInvoiceEntityMapper;
import com.siit.spring.mapper.InvoiceEntityToInvoiceDtoMapper;
import com.siit.spring.repository.InvoiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceDtoToInvoiceEntityMapper invoiceDtoToInvoiceEntityMapper;
    private final InvoiceEntityToInvoiceDtoMapper invoiceEntityToInvoiceDtoMapper;

    public InvoiceDto create(InvoiceDto invoiceDto) {
        Invoice invoice = invoiceDtoToInvoiceEntityMapper.convert(invoiceDto);

        if (null == invoice) {
            throw new NullPointerException("Invoice cannot be null!");
        }

        invoice = invoiceRepository.save(invoice);
        return invoiceEntityToInvoiceDtoMapper.convert(invoice);
    }

    public InvoiceDto findById(long invoiceId) {
        return invoiceRepository.findById(invoiceId)
                .map(invoiceEntityToInvoiceDtoMapper::convert)
                .orElseThrow(() -> new InvoiceNotFoundException("The invoice with provided id cannot be found!"));
    }

    public List<InvoiceDto> getAll() {
        return invoiceRepository.getAll()
                .stream()
                .map(invoiceEntityToInvoiceDtoMapper::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void update(InvoiceDto invoiceDto) {
        Invoice invoice = invoiceRepository.findById(invoiceDto.getInvoiceId())
                .orElseThrow(() -> new InvoiceNotFoundException("Invoice with provided id cannot be found!"));

        updateFields(invoice, invoiceDto);
    }

    private void updateFields(Invoice invoice, InvoiceDto invoiceDto) {
        if (null != invoiceDto.getInvoiceNumber()) {
            invoice.setInvoiceNumber(invoiceDto.getInvoiceNumber());
        }

        if (null != invoiceDto.getStatus()) {
            invoice.setStatus(invoiceDto.getStatus());
        }

        if (null != invoiceDto.getType()) {
            invoice.setType(invoiceDto.getType());
        }

        invoice.setDateModified(LocalDateTime.now());
    }

    public void delete(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new InvoiceNotFoundException("The invoice with provided id cannot be found!"));

        invoiceRepository.delete(invoice);
    }
}
