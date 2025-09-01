package com.store.migros.mapper;

import com.store.migros.dto.ProductDto;
import com.store.migros.model.Product;

public class ProductMapper {

	public static ProductDto toDto(Product product) {
		ProductDto dto = new ProductDto();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		dto.setStock(product.getStock());
		return dto;
	}

	public static Product toEntity(ProductDto dto) {
		Product product = new Product();
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setStock(dto.getStock());
		return product;
	}
}
