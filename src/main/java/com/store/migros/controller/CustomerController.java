package com.store.migros.controller;

import com.store.migros.dto.CustomerDto;
import com.store.migros.service.CustomerService;

import org.hibernate.mapping.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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

	@PostMapping("/signup")
	public ResponseEntity<java.util.Map<String, String>> signup(@RequestBody CustomerDto dto) {
	    try {
	        CustomerDto created = customerService.createCustomer(dto);
	        return ResponseEntity.ok(Collections.singletonMap("message", "Account created successfully"));
	    } catch (RuntimeException e) {
	        return ResponseEntity
	                .status(HttpStatus.BAD_REQUEST)
	                .body(Collections.singletonMap("error", e.getMessage()));
	    }
	}


	@PostMapping("/login")
	public CustomerDto login(@RequestBody CustomerDto dto) {
		return customerService.login(dto.getEmail(), dto.getPassword());
	}

}
