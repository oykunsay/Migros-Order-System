package com.store.migros.repository;

import com.store.migros.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByCategory(String category);
	
}
