package com.store.migros.service;

import com.store.migros.dto.CustomerDto;
import com.store.migros.mapper.CustomerMapper;
import com.store.migros.model.Customer;
import com.store.migros.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<CustomerDto> getAllCustomers() {
		return customerRepository.findAll().stream().map(CustomerMapper::toDto).collect(Collectors.toList());
	}

	public CustomerDto getCustomerById(Long id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found"));
		return CustomerMapper.toDto(customer);
	}

	public CustomerDto createCustomer(CustomerDto dto) {

		if (customerRepository.findByEmail(dto.getEmail()).isPresent()) {
			throw new RuntimeException("Email is already in use");
		}
		Customer customer = CustomerMapper.toEntity(dto);
		Customer saved = customerRepository.save(customer);
		return CustomerMapper.toDto(saved);
	}

	public CustomerDto updateCustomer(Long id, CustomerDto dto) {
		Customer existing = customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		existing.setName(dto.getName());
		existing.setEmail(dto.getEmail());
		if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
			existing.setPassword(dto.getPassword());
		}
		existing.setPhoneNumber(dto.getPhoneNumber());

		Customer updated = customerRepository.save(existing);
		return CustomerMapper.toDto(updated);
	}

	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}

	public CustomerDto login(String email, String password) {
		Customer customer = customerRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (!customer.getPassword().equals(password)) {
			throw new RuntimeException("Invalid credentials");
		}

		return CustomerMapper.toDto(customer);
	}

}
