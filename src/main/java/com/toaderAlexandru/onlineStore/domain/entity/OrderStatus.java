package com.toaderAlexandru.onlineStore.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "order_status")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    @OneToMany(mappedBy = "orderStatus")
    private List<Order> order;
}
