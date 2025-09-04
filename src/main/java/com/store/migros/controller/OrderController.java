package com.store.migros.controller;

import com.store.migros.dto.OrderDto;
import com.store.migros.service.OrderService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public List<OrderDto> getAllOrders() {
		return orderService.getAllOrders();
	}

	@GetMapping("/{id}")
	public OrderDto getOrderById(@PathVariable Long id) {
		return orderService.getOrderById(id);
	}

	@PostMapping
	public OrderDto createOrder(@RequestBody OrderDto dto) {
		return orderService.createOrder(dto);
	}

	@PutMapping("/{id}")
	public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto dto) {
		return orderService.updateOrder(id, dto);
	}

	@DeleteMapping("/{id}")
	public void deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
	}
}
