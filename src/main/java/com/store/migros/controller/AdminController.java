package com.store.migros.controller;

import com.store.migros.dto.AdminDto;
import com.store.migros.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@GetMapping
	public List<AdminDto> getAllAdmins() {
		return adminService.getAllAdmins();
	}

	@GetMapping("/{id}")
	public AdminDto getAdminById(@PathVariable Long id) {
		return adminService.getAdminById(id);
	}

	@PostMapping
	public AdminDto createAdmin(@RequestBody AdminDto dto, @RequestParam String password) {
		return adminService.createAdmin(dto, password);
	}

	@PutMapping("/{id}")
	public AdminDto updateAdmin(@PathVariable Long id, @RequestBody AdminDto dto) {
		return adminService.updateAdmin(id, dto);
	}

	@DeleteMapping("/{id}")
	public void deleteAdmin(@PathVariable Long id) {
		adminService.deleteAdmin(id);
	}
}
