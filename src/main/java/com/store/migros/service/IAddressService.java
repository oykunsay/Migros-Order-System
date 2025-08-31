package com.store.migros.service;

import java.util.List;

import com.store.migros.model.Address;

public interface IAddressService {
	List<Address> getAllAddresses();

	Address getAddressById(Long id);

	Address createAddress(Address address);

	Address updateAddress(Long id, Address address);

	void deleteAddress(Long id);
	
	List<Address> getAddressesByCustomerId(Long customerId);


}
