package com.store.migros.service;

import com.store.migros.dto.AddressDto;
import com.store.migros.mapper.AddressMapper;
import com.store.migros.model.Address;
import com.store.migros.model.Customer;
import com.store.migros.repository.AddressRepository;
import com.store.migros.repository.CustomerRepository;

import jakarta.transaction.Transactional;

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

	@Transactional
	public AddressDto updateAddress(Long customerId, Long id, AddressDto dto) {
		Address address = addressRepository.findByIdAndCustomerId(id, customerId)
				.orElseThrow(() -> new RuntimeException("Address not found for this customer"));

		address.setAddressDetails(dto.getAddressDetails());
		address.setCity(dto.getCity());
		address.setDistrict(dto.getDistrict());
		address.setAddressType(dto.getAddressType());

		Address updated = addressRepository.save(address);

		System.out.println("Updating address " + id + " for customer " + customerId + ": " + dto);

		return AddressMapper.toDto(updated);
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
