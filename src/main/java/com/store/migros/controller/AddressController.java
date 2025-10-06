package com.store.migros.controller;

import com.store.migros.dto.AddressDto;
import com.store.migros.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers/{customerId}/addresses")
public class AddressController {

	private final AddressService addressService;

	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@GetMapping
	public List<AddressDto> getAddressesByCustomer(@PathVariable Long customerId) {
		return addressService.getAddressesByCustomerId(customerId);
	}

	@PutMapping("/{id}")
	public AddressDto updateAddress(@PathVariable Long customerId, @PathVariable Long id, @RequestBody AddressDto dto) {
		return addressService.updateAddress(customerId, id, dto);
	}

	@PostMapping
	public AddressDto createAddress(@PathVariable Long customerId, @RequestBody AddressDto dto) {
		return addressService.createAddressForCustomer(customerId, dto);
	}

	@DeleteMapping("/{id}")
	public void deleteAddress(@PathVariable Long customerId, @PathVariable Long id) {
		addressService.deleteAddress(id);
	}
}
