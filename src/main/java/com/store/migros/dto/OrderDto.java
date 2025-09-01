package com.store.migros.dto;

import java.time.LocalDate;
import java.util.List;

public class OrderDto {
	private Long id;
	private Long customerId;
	private LocalDate orderDate;
	private List<OrderDetailsDto> orderDetails;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public List<OrderDetailsDto> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailsDto> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
