package com.store.migros.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.store.migros.model.Admin;
import com.store.migros.repository.AdminRepository;

@Service
public class AdminServiceImpl implements IAdminService {

	private final AdminRepository adminRepository;

	public AdminServiceImpl(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

	@Override
	public List<Admin> getAllAdmins() {
		return adminRepository.findAll();
	}

	@Override
	public Admin getAdminById(Long id) {
		Optional<Admin> adminOptional = adminRepository.findById(id);

		if (adminOptional.isPresent()) {
			return adminOptional.get();
		} else {
			throw new RuntimeException("Admin not found with id: " + id);
		}
	}

	@Override
	public Admin createAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public Admin updateAdmin(Long id, Admin updatedAdmin) {
		Optional<Admin> existingAdminOptional = adminRepository.findById(id);

		if (existingAdminOptional.isPresent()) {
			Admin existingAdmin = existingAdminOptional.get();
			existingAdmin.setName(updatedAdmin.getName());
			existingAdmin.setEmail(updatedAdmin.getEmail());
			existingAdmin.setPhoneNumber(updatedAdmin.getPhoneNumber());

			return adminRepository.save(existingAdmin);
		} else {
			throw new RuntimeException("Admin not found with id: " + id);
		}
	}

	@Override
	public void deleteAdmin(Long id) {
		adminRepository.deleteById(id);

	}

}
