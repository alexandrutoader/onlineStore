package com.toaderAlexandru.onlineStore.repository;

import com.toaderAlexandru.onlineStore.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u " +
            "order by u.id")
    List<User> getAll();

    @Query("select u from User u " +
            "where u.email = :email")
    User findByEmail(@Param("email") String email);
}
