package com.toaderAlexandru.onlineStore.repository;

import com.toaderAlexandru.onlineStore.domain.entity.CartProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProducts, Long> {
    @Query("select c from CartProducts c " +
            "order by c.id")
    List<CartProducts> getAll();
}
