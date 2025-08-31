package com.store.migros.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.store.migros.model.Admin;
import com.store.migros.service.IAdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	private final IAdminService adminService;

	public AdminController(IAdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping
	public List<Admin> getAllAdmins() {
		return adminService.getAllAdmins();
	}

	@GetMapping("/{id}")
	public Admin getAdminById(@PathVariable Long id) {
		return adminService.getAdminById(id);
	}

	@PostMapping
	public Admin createAdmin(@PathVariable Long id, @RequestBody Admin admin) {
		return adminService.createAdmin(admin);
	}

	@PutMapping("/{id}")
	public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
		return adminService.updateAdmin(id, admin);
	}

	@DeleteMapping("/{id}")
	public void deleteAdmin(@PathVariable Long id) {
		adminService.deleteAdmin(id);
	}
}
