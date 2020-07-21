package com.siit.spring.repository;

import com.siit.spring.domain.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("select i from Invoice i " +
            "order by i.invoiceId")
    List<Invoice> getAll();
}
