package com.store.migros.mapper;

import com.store.migros.dto.AddressDto;
import com.store.migros.model.Address;

public class AddressMapper {

	public static AddressDto toDto(Address address) {
		return new AddressDto(address.getAddressDetails(), address.getCity(), address.getDistrict(),
				address.getAddressType());
	}

	public static Address toEntity(AddressDto dto) {
		return new Address(null, dto.getAddressDetails(), dto.getCity(), dto.getDistrict(), dto.getAddressType(), null

		);
	}
}
