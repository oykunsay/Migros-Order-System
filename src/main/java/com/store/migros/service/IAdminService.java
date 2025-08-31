package com.store.migros.service;

import java.util.List;

import com.store.migros.model.Admin;

public interface IAdminService {
	List<Admin> getAllAdmins();
	Admin getAdminById(Long id);
	Admin createAdmin(Admin admin);
    Admin updateAdmin(Long id, Admin admin);
    void deleteAdmin(Long id);

}
