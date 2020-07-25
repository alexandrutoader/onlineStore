package com.siit.spring.repository;

import com.siit.spring.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("select a from Address a " +
            "order by a.addressId")
    List<Address> getAll();

    @Query("select a from Address a " +
            "where a.addressId = :addressId")
    List<Address> getAddressById(@Param("addressId") Long addressId);

    @Query("select a from Address a " +
            "where a.customer = :customerId")
    Address getAddressByCustomerId(@Param("customerId") Long customerId);
}
