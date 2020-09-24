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
@Table(name = "payment")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private Integer status;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @OneToMany(mappedBy = "payment")
    private List<Order> order;
}
