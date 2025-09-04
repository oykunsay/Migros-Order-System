package com.store.migros.mapper;

import com.store.migros.dto.OrderDetailsDto;
import com.store.migros.model.OrderDetails;
import com.store.migros.model.Product;

public class OrderDetailsMapper {

	public static OrderDetailsDto toDto(OrderDetails detail) {
		return OrderDetailsDto.builder().productId(detail.getProduct() != null ? detail.getProduct().getId() : null)
				.quantity(detail.getQuantity())
				.price(detail.getProduct() != null ? detail.getProduct().getPrice() * detail.getQuantity() : 0.0)
				.build();
	}

	public static OrderDetails toEntity(OrderDetailsDto dto) {
		OrderDetails details = new OrderDetails();
		details.setQuantity(dto.getQuantity());

		Product product = new Product();
		product.setId(dto.getProductId());
		details.setProduct(product);

		return details;
	}
}
