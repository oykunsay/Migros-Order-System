package com.store.migros.mapper;

import com.store.migros.dto.AdminDto;
import com.store.migros.model.Admin;

public class AdminMapper {

	public static AdminDto toDto(Admin admin) {
		return new AdminDto(admin.getName(), admin.getEmail(), admin.getPhoneNumber());
	}

	public static Admin toEntity(AdminDto dto) {
		Admin admin = new Admin();
		admin.setName(dto.getName());
		admin.setEmail(dto.getEmail());
		admin.setPhoneNumber(dto.getPhoneNumber());
		return admin;
	}
}
