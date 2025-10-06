package com.store.migros.mapper;

import com.store.migros.dto.CustomerDto;
import com.store.migros.dto.AddressDto;
import com.store.migros.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {

	public static CustomerDto toDto(Customer customer) {
		List<AddressDto> addresses = new ArrayList<>();
		if (customer.getAddresses() != null) {
			for (var address : customer.getAddresses()) {
				addresses.add(AddressMapper.toDto(address));
			}
		}

		return CustomerDto.builder().id(customer.getId()).name(customer.getName()).email(customer.getEmail())
				.password(customer.getPassword()).phoneNumber(customer.getPhoneNumber()).addresses(addresses).build();
	}

	public static Customer toEntity(CustomerDto dto) {
		return Customer.builder().name(dto.getName()).email(dto.getEmail()).password(dto.getPassword())
				.phoneNumber(dto.getPhoneNumber()).build();
	}
}