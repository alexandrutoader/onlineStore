package com.toaderAlexandru.onlineStore.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "cart_products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartProducts {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    private Long quantity;

    private Integer status;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;
}
