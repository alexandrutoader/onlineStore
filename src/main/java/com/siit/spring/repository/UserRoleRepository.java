package com.siit.spring.repository;

import com.siit.spring.domain.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("select u from UserRole u " +
            "order by u.id")
    List<UserRole> getAll();
}
