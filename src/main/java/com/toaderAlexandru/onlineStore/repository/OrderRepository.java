package com.toaderAlexandru.onlineStore.repository;

import com.toaderAlexandru.onlineStore.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o " +
            "order by o.orderId")
    List<Order> getAll();
}
