package com.store.migros.controller;

import com.store.migros.dto.ProductDto;
import com.store.migros.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<ProductDto> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{id}")
	public ProductDto getProductById(@PathVariable Long id) {
		return productService.getProductById(id);
	}

	@GetMapping("/category/{category}")
	public List<ProductDto> getProductsByCategory(@PathVariable String category) {
		return productService.getProductsByCategory(category);
	}

	@PostMapping
	public ProductDto createProduct(@RequestBody ProductDto dto) {
		return productService.createProduct(dto);
	}

	@PutMapping("/{id}")
	public ProductDto updateProduct(@PathVariable Long id, @RequestBody ProductDto dto) {
		return productService.updateProduct(id, dto);
	}

	@PostMapping("/{id}/image")
	public ProductDto updateProductImage(@PathVariable Long id, @RequestParam("file") MultipartFile file)
			throws Exception {
		return productService.updateProductImage(id, file);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}
}
