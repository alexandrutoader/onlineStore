package com.siit.spring.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    private String name;

    private BigDecimal price;

    private Long quantity;

    private Long reserved;

    private String image;

    private String currency;

    private Integer status;

    @Column(name = "added_by")
    private Integer addedBy;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    @OneToMany(mappedBy = "product")
    private List<CartProducts> cartProduct;
}
