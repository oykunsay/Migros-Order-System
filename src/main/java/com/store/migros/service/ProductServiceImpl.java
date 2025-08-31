package com.store.migros.service;

import com.store.migros.model.Product;
import com.store.migros.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Long id, Product product) {
		Product existing = getProductById(id);
		existing.setName(product.getName());
		existing.setPrice(product.getPrice());
		existing.setStock(product.getStock());
		return productRepository.save(existing);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
