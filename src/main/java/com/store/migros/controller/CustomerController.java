package com.store.migros.controller;

import com.store.migros.dto.CustomerDto;
import com.store.migros.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping
	public List<CustomerDto> getAllCustomers() {
		return customerService.getAllCustomers();
	}

	@GetMapping("/{id}")
	public CustomerDto getCustomerById(@PathVariable Long id) {
		return customerService.getCustomerById(id);
	}

	@PostMapping
	public CustomerDto createCustomer(@RequestBody CustomerDto dto) {
		return customerService.createCustomer(dto);
	}

	@PutMapping("/{id}")
	public CustomerDto updateCustomer(@PathVariable Long id, @RequestBody CustomerDto dto) {
		return customerService.updateCustomer(id, dto);
	}

	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
	}
}
