package com.store.migros.service;

import com.store.migros.dto.AdminDto;
import com.store.migros.mapper.AdminMapper;
import com.store.migros.model.Admin;
import com.store.migros.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

	private final AdminRepository adminRepository;

	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	public List<AdminDto> getAllAdmins() {
		List<Admin> admins = adminRepository.findAll();
		List<AdminDto> dtos = new ArrayList<>();
		for (Admin admin : admins) {
			dtos.add(AdminMapper.toDto(admin));
		}
		return dtos;
	}

	public AdminDto createAdmin(AdminDto dto) {
		Admin admin = AdminMapper.toEntity(dto);
		Admin saved = adminRepository.save(admin);
		return AdminMapper.toDto(saved);
	}

	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);
	}
}
