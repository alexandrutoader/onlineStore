package com.siit.spring.repository;

import com.siit.spring.domain.entity.Order;
import com.siit.spring.domain.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    @Query("select o from OrderStatus o " +
            "order by o.sortOrder")
    List<Order> getAll();
}
