package com.store.migros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.migros.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
	List<Address> findByCustomerId(Long customerId);
}
