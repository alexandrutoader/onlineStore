package com.siit.spring.repository;

import com.siit.spring.domain.entity.Order;
import com.siit.spring.domain.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("select p from Payment p " +
            "order by p.id")
    List<Order> getAll();
}
