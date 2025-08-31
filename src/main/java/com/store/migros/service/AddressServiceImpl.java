package com.store.migros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.store.migros.model.Address;
import com.store.migros.repository.AddressRepository;

@Service
public class AddressServiceImpl implements IAddressService {

	private final AddressRepository addressRepository;

	public AddressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Override
	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}

	@Override
	public Address getAddressById(Long id) {
		Optional<Address> addressOptional = addressRepository.findById(id);

		if (addressOptional.isPresent()) {
			return addressOptional.get();
		} else {
			throw new RuntimeException("Address not found with id: " + id);
		}
	}

	@Override
	public Address createAddress(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public Address updateAddress(Long id, Address address) {
		Optional<Address> existingAddressOptional = addressRepository.findById(id);
		if (existingAddressOptional.isPresent()) {
			Address existingAddress = existingAddressOptional.get();
			existingAddress.setAddressDetails(address.getAddressDetails());
			existingAddress.setCity(address.getCity());
			existingAddress.setDistrict(address.getDistrict());
			existingAddress.setAddressType(address.getAddressType());
		}
		return null;
	}

	@Override
	public void deleteAddress(Long id) {
		addressRepository.deleteById(id);
	}

	@Override
	public List<Address> getAddressesByCustomerId(Long customerId) {
		return addressRepository.findByCustomerId(customerId);
	}
}
