package com.store.migros.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

	private Long id;
	private String name;
	private Double price;
	private Integer stock;
	private String imageUrl;
	private String category;
}
