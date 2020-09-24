package com.toaderAlexandru.onlineStore.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "invoice")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "invoice_id")
    private Long invoiceId;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "invoice_number")
    private Long invoiceNumber;

    private Integer type;

    private Integer status;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;
}
