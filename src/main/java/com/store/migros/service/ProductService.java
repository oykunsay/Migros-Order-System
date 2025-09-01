package com.store.migros.service;

import com.store.migros.dto.ProductDto;
import com.store.migros.mapper.ProductMapper;
import com.store.migros.model.Product;
import com.store.migros.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<ProductDto> getAllProducts() {
		return productRepository.findAll().stream().map(ProductMapper::toDto).collect(Collectors.toList());
	}

	public ProductDto getProductById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
		return ProductMapper.toDto(product);
	}

	public ProductDto createProduct(ProductDto dto) {
		Product product = ProductMapper.toEntity(dto);
		product = productRepository.save(product);
		return ProductMapper.toDto(product);
	}

	public ProductDto updateProduct(Long id, ProductDto dto) {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setStock(dto.getStock());

		product = productRepository.save(product);
		return ProductMapper.toDto(product);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
