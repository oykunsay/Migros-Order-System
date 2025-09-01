package com.store.migros.mapper;

import com.store.migros.dto.CustomerDto;
import com.store.migros.model.Customer;

public class CustomerMapper {

	public static CustomerDto toDto(Customer customer) {
		CustomerDto dto = new CustomerDto();
		dto.setId(customer.getId());
		dto.setName(customer.getName());
		dto.setEmail(customer.getEmail());
		dto.setPhoneNumber(customer.getPhoneNumber());
		return dto;
	}

	public static Customer toEntity(CustomerDto dto) {
		Customer customer = new Customer();
		customer.setName(dto.getName());
		customer.setEmail(dto.getEmail());
		customer.setPhoneNumber(dto.getPhoneNumber());
		return customer;
	}
}
