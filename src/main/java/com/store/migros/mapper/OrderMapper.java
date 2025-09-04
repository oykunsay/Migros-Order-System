package com.store.migros.mapper;

import com.store.migros.dto.OrderDto;
import com.store.migros.dto.OrderDetailsDto;
import com.store.migros.model.Order;
import com.store.migros.model.OrderDetails;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

	public static OrderDto toDto(Order order) {
		List<OrderDetailsDto> detailsDtoList = new ArrayList<>();
		if (order.getOrderDetails() != null) {
			for (OrderDetails detail : order.getOrderDetails()) {
				detailsDtoList.add(OrderDetailsMapper.toDto(detail));
			}
		}

		return OrderDto.builder().id(order.getId())
				.customerId(order.getCustomer() != null ? order.getCustomer().getId() : null)
				.orderDate(order.getOrderDate()).totalPrice(order.getTotalPrice()).orderDetails(detailsDtoList).build();
	}

	public static Order toEntity(OrderDto dto) {
		Order order = new Order();
		order.setOrderDate(dto.getOrderDate());

		List<OrderDetails> detailsList = new ArrayList<>();
		if (dto.getOrderDetails() != null) {
			for (OrderDetailsDto detailDto : dto.getOrderDetails()) {
				OrderDetails detail = OrderDetailsMapper.toEntity(detailDto);
				detail.setOrder(order); 
				detailsList.add(detail);
			}
		}
		order.setOrderDetails(detailsList);

		return order;
	}
}
