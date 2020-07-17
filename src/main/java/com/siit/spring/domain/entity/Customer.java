package com.siit.spring.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "customer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String telephone;

    private String password;

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "agent_id")
    private Long agentId;

    private Integer status;

    @Column(name = "date_added")
    private LocalDate dateAdded;

    @Column(name = "date_modified")
    private LocalDate dateModified;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    @OneToOne(mappedBy = "customer")
    private Cart cart;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}
