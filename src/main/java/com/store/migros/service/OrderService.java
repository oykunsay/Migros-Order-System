package com.store.migros.service;

import com.store.migros.dto.OrderDto;
import com.store.migros.mapper.OrderMapper;
import com.store.migros.model.Customer;
import com.store.migros.model.Order;
import com.store.migros.model.OrderDetails;
import com.store.migros.model.Product;
import com.store.migros.repository.CustomerRepository;
import com.store.migros.repository.OrderRepository;
import com.store.migros.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

	private final OrderRepository orderRepository;
	private final CustomerRepository customerRepository;
	private final ProductRepository productRepository;

	public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository,
			ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
	}

	public List<OrderDto> getAllOrders() {
		List<Order> orders = orderRepository.findAllWithDetailsAndProducts();
		return orders.stream().map(OrderMapper::toDto).collect(Collectors.toList());
	}

	public List<OrderDto> getOrdersByCustomerId(Long customerId) {
		List<Order> orders = orderRepository.findByCustomerIdWithDetailsAndProducts(customerId);
		return orders.stream().map(OrderMapper::toDto).collect(Collectors.toList());
	}

	public OrderDto getOrderById(Long id) {
		Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
		return OrderMapper.toDto(order);
	}

	public OrderDto createOrder(OrderDto dto) {
		Order order = OrderMapper.toEntity(dto);

		if (order.getOrderDate() == null) {
			order.setOrderDate(LocalDate.now()); 
		}

		if (dto.getCustomerId() != null) {
			Customer customer = customerRepository.findById(dto.getCustomerId())
					.orElseThrow(() -> new RuntimeException("Customer not found"));
			order.setCustomer(customer);
		}

		double totalPrice = 0.0;
		for (OrderDetails detail : order.getOrderDetails()) {
			Product product = productRepository.findById(detail.getProduct().getId())
					.orElseThrow(() -> new RuntimeException("Product not found"));

			if (product.getStock() < detail.getQuantity()) {
				throw new RuntimeException("Not enough stock for product: " + product.getName());
			}

			product.setStock(product.getStock() - detail.getQuantity());
			productRepository.save(product);

			detail.setProduct(product);
			totalPrice += product.getPrice() * detail.getQuantity();
		}

		order.setTotalPrice(totalPrice);

		Order saved = orderRepository.save(order);
		return OrderMapper.toDto(saved);
	}

	public OrderDto updateOrder(Long id, OrderDto dto) {
		Order existing = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));

		existing.setOrderDate(dto.getOrderDate());

		if (dto.getCustomerId() != null) {
			Customer customer = customerRepository.findById(dto.getCustomerId())
					.orElseThrow(() -> new RuntimeException("Customer not found"));
			existing.setCustomer(customer);
		}

		double totalPrice = 0.0;
		for (OrderDetails detail : existing.getOrderDetails()) {
			Product product = productRepository.findById(detail.getProduct().getId())
					.orElseThrow(() -> new RuntimeException("Product not found"));
			detail.setProduct(product);
			totalPrice += product.getPrice() * detail.getQuantity();
		}
		existing.setTotalPrice(totalPrice);

		Order updated = orderRepository.save(existing);
		return OrderMapper.toDto(updated);
	}

	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}
}
