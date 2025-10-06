package com.store.migros.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsDto {

	private Long productId;
	private String productName;

	private int quantity;
	private Double price;
}
