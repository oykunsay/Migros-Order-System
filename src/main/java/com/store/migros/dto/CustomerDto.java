package com.store.migros.dto;

import lombok.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
	private Long id;
	private String name;
	private String email;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	private String phoneNumber;
	private List<AddressDto> addresses;
}