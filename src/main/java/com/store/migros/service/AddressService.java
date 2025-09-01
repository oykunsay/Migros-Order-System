package com.store.migros.service;

import com.store.migros.dto.AddressDto;
import com.store.migros.model.Address;
import com.store.migros.model.Customer;
import com.store.migros.repository.AddressRepository;
import com.store.migros.repository.CustomerRepository;
import com.store.migros.mapper.AddressMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
		return customer.getAddresses().stream().map(AddressMapper::toDto).collect(Collectors.toList());
	}

	public AddressDto createAddressForCustomer(Long customerId, AddressDto dto) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));
		Address address = new Address();
		address.setAddressDetails(dto.getAddressDetails());
		address.setCity(dto.getCity());
		address.setDistrict(dto.getDistrict());
		address.setAddressType(dto.getAddressType());
		address.setCustomer(customer);
		Address saved = addressRepository.save(address);
		return AddressMapper.toDto(saved);
	}

	public void deleteAddress(Long id) {
		addressRepository.deleteById(id);
	}
}
