package com.store.migros.repository;

import com.store.migros.model.Address;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByIdAndCustomerId(Long id, Long customerId);

}
