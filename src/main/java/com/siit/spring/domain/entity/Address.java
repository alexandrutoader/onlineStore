package com.siit.spring.domain.entity;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "address")
@Data
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @Nullable
    private Customer customer;

    @Column(name = "first_name")
    @Nullable
    private String firstName;

    @Column(name = "last_name")
    @Nullable
    private String lastName;

    @Nullable
    private String telephone;

    @Column(name = "address_name")
    @Nullable
    private String addressName;

    @Nullable
    private String city;

    @Column(name = "postal_code")
    @Nullable
    private String postalCode;

    @Nullable
    private Integer status;

    @Nullable
    @Column(name = "date_added")
    private LocalDate dateAdded;

    @Nullable
    @Column(name = "date_modified")
    private LocalDate dateModified;
}
