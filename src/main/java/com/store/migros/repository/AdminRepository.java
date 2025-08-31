package com.store.migros.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.migros.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
