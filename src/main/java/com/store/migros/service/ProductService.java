package com.store.migros.service;

import com.store.migros.dto.ProductDto;
import com.store.migros.mapper.ProductMapper;
import com.store.migros.model.Product;
import com.store.migros.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductDto> getAllProducts() {
		List<Product> products = productRepository.findAll();
		List<ProductDto> dtos = new ArrayList<>();
		for (Product product : products) {
			dtos.add(ProductMapper.toDto(product));
		}
		return dtos;
	}

	public ProductDto getProductById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		return ProductMapper.toDto(product);
	}

	public ProductDto createProduct(ProductDto dto) {
		Product product = ProductMapper.toEntity(dto);
		Product saved = productRepository.save(product);
		return ProductMapper.toDto(saved);
	}

	public ProductDto updateProduct(Long id, ProductDto dto) {
		Product existing = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

		existing.setName(dto.getName());
		existing.setPrice(dto.getPrice());
		existing.setStock(dto.getStock());

		Product updated = productRepository.save(existing);
		return ProductMapper.toDto(updated);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
