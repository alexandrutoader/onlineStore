package com.toaderAlexandru.onlineStore.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "`order`")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<Invoice> invoices;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    private BigDecimal total;

    private String currency;

    @Nullable
    private Long invoiceId;

    @ManyToOne
    @JoinColumn(name = "order_status_id", referencedColumnName = "id")
    private OrderStatus orderStatus;

    private Integer status;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;
}
