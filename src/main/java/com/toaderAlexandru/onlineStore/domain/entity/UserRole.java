package com.toaderAlexandru.onlineStore.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_role")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String role;

    private Integer status;

    @Column(name = "date_added")
    private LocalDateTime dateAdded;

    @OneToOne(mappedBy = "userRole")
    private User user;
}
