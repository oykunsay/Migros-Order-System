package com.store.migros.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {
	private String name;
	private String email;
	private String phoneNumber;
}
