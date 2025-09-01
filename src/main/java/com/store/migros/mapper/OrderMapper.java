package com.store.migros.mapper;

import com.store.migros.dto.OrderDto;
import com.store.migros.dto.OrderDetailsDto;
import com.store.migros.model.Order;

import java.util.stream.Collectors;

public class OrderMapper {

	public static OrderDto toDto(Order order) {
		OrderDto dto = new OrderDto();
		dto.setId(order.getId());
		dto.setCustomerId(order.getCustomer() != null ? order.getCustomer().getId() : null);
		dto.setOrderDate(order.getOrderDate());

		if (order.getOrderDetails() != null) {
			dto.setOrderDetails(order.getOrderDetails().stream().map(od -> {
				OrderDetailsDto info = new OrderDetailsDto();
				info.setProductId(od.getProduct().getId());
				info.setQuantity(od.getQuantity());
				return info;
			}).collect(Collectors.toList()));
		}

		return dto;
	}
}
