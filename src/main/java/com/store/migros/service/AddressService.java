package com.store.migros.service;

import com.store.migros.dto.AddressDto;
import com.store.migros.mapper.AddressMapper;
import com.store.migros.model.Address;
import com.store.migros.model.Customer;
import com.store.migros.repository.AddressRepository;
import com.store.migros.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

	private final AddressRepository addressRepository;
	private final CustomerRepository customerRepository;

	public AddressService(AddressRepository addressRepository, CustomerRepository customerRepository) {
		this.addressRepository = addressRepository;
		this.customerRepository = customerRepository;
	}

	public List<AddressDto> getAddressesByCustomerId(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		List<AddressDto> dtos = new ArrayList<>();
		for (Address address : customer.getAddresses()) {
			dtos.add(AddressMapper.toDto(address));
		}
		return dtos;
	}

	public AddressDto createAddressForCustomer(Long customerId, AddressDto dto) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		Address address = AddressMapper.toEntity(dto);
		address.setCustomer(customer);

		Address saved = addressRepository.save(address);
		return AddressMapper.toDto(saved);
	}

	public void deleteAddress(Long id) {
		addressRepository.deleteById(id);
	}
}
