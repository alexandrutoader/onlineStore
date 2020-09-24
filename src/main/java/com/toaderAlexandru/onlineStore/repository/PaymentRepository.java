package com.toaderAlexandru.onlineStore.repository;

import com.toaderAlexandru.onlineStore.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("select p from Payment p " +
            "order by p.id")
    List<Payment> getAll();
}
