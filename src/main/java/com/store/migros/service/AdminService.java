package com.store.migros.service;

import com.store.migros.dto.AdminDto;
import com.store.migros.mapper.AdminMapper;
import com.store.migros.model.Admin;
import com.store.migros.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

	private final AdminRepository adminRepository;

	public AdminService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	public List<AdminDto> getAllAdmins() {
		return adminRepository.findAll().stream().map(AdminMapper::toDto).collect(Collectors.toList());
	}

	public AdminDto getAdminById(Long id) {
		Admin admin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
		return AdminMapper.toDto(admin);
	}

	public AdminDto createAdmin(AdminDto dto, String password) {
		Admin admin = AdminMapper.toEntity(dto);
		admin.setPassword(password); 
		admin = adminRepository.save(admin);
		return AdminMapper.toDto(admin);
	}

	public AdminDto updateAdmin(Long id, AdminDto dto) {
		Admin admin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));

		admin.setName(dto.getName());
		admin.setEmail(dto.getEmail());
		admin.setPhoneNumber(dto.getPhoneNumber());

		admin = adminRepository.save(admin);
		return AdminMapper.toDto(admin);
	}

	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);
	}
}
