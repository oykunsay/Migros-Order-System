package com.store.migros.service;

import com.store.migros.dto.ProductDto;
import com.store.migros.model.Product;
import com.store.migros.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService(ProductRepository repository) {
		this.productRepository = repository;
	}

	public List<ProductDto> getAllProducts() {
		return productRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	public ProductDto getProductById(Long id) {
		return productRepository.findById(id).map(this::toDto).orElse(null);
	}

	public List<ProductDto> getProductsByCategory(String category) {
		return productRepository.findByCategory(category).stream().map(this::toDto).collect(Collectors.toList());
	}

	public ProductDto createProduct(ProductDto dto) {
		Product product = Product.builder().name(dto.getName()).price(dto.getPrice()).stock(dto.getStock())
				.category(dto.getCategory()).imageUrl(dto.getImageUrl()).build();
		productRepository.save(product);
		return toDto(product);
	}

	public ProductDto updateProductImage(Long id, MultipartFile file) throws Exception {
		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		Path uploadDir = Paths.get("uploads");
		if (!Files.exists(uploadDir)) {
			Files.createDirectories(uploadDir);
		}
		Path path = uploadDir.resolve(fileName);
		Files.copy(file.getInputStream(), path);

		product.setImageUrl("http://localhost:8080/uploads/" + fileName);
		productRepository.save(product);

		return toDto(product);
	}

	public ProductDto updateProduct(Long id, ProductDto dto) {
		Product product = productRepository.findById(id).orElseThrow();
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setStock(dto.getStock());
		product.setCategory(dto.getCategory());
		product.setImageUrl(dto.getImageUrl());
		productRepository.save(product);
		return toDto(product);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	private ProductDto toDto(Product product) {
		return ProductDto.builder().id(product.getId()).name(product.getName()).price(product.getPrice())
				.stock(product.getStock()).category(product.getCategory()).imageUrl(product.getImageUrl()).build();
	}
}
