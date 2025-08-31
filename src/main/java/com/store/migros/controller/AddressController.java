package com.store.migros.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.store.migros.model.Address;
import com.store.migros.model.Customer;
import com.store.migros.service.IAddressService;
import com.store.migros.service.ICustomerService;

@RestController
@RequestMapping("/customers/{customerId}/addresses")
public class AddressController {

	private final IAddressService addressService;
	private final ICustomerService customerService;

	public AddressController(IAddressService addressService, ICustomerService customerService) {
		this.addressService = addressService;
		this.customerService = customerService;
	}

	@GetMapping
	public List<Address> getAllAddresses(@PathVariable Long customerId) {
		return addressService.getAddressesByCustomerId(customerId);
	}

	@GetMapping("/{id}")
	public Address getAddressById(@PathVariable Long customerId, @PathVariable Long id) {
		return addressService.getAddressById(id);
	}

	@PostMapping
	public Address createAddress(@PathVariable Long customerId, @RequestBody Address address) {
		Customer customer = customerService.getCustomerById(customerId);
		address.setCustomer(customer);
		return addressService.createAddress(address);
	}

	@PutMapping("/{id}")
	public Address updateAddress(@PathVariable Long customerId, @PathVariable Long id, @RequestBody Address address) {
		Customer customer = customerService.getCustomerById(customerId);
		address.setCustomer(customer);
		return addressService.updateAddress(id, address);
	}

	@DeleteMapping("/{id}")
	public void deleteAddress(@PathVariable Long customerId, @PathVariable Long id) {
		addressService.deleteAddress(id);
	}
}
