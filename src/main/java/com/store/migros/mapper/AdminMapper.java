package com.store.migros.mapper;

import com.store.migros.dto.AdminDto;
import com.store.migros.model.Admin;

public class AdminMapper {

	public static AdminDto toDto(Admin admin) {
		AdminDto dto = new AdminDto();
		dto.setId(admin.getId());
		dto.setName(admin.getName());
		dto.setEmail(admin.getEmail());
		dto.setPhoneNumber(admin.getPhoneNumber());
		return dto;
	}

	public static Admin toEntity(AdminDto dto) {
		Admin admin = new Admin();
		admin.setName(dto.getName());
		admin.setEmail(dto.getEmail());
		admin.setPhoneNumber(dto.getPhoneNumber());
		return admin;
	}
}
