package com.store.migros.mapper;

import com.store.migros.dto.AddressDto;
import com.store.migros.model.Address;

public class AddressMapper {
	public static AddressDto toDto(Address address) {
		AddressDto dto = new AddressDto();
		dto.setId(address.getId());
		dto.setAddressDetails(address.getAddressDetails());
		dto.setCity(address.getCity());
		dto.setDistrict(address.getDistrict());
		dto.setAddressType(address.getAddressType());
		return dto;
	}
}
