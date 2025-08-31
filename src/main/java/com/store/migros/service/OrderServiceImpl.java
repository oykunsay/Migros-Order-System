package com.store.migros.service;

import com.store.migros.model.Order;
import com.store.migros.model.Customer;
import com.store.migros.model.OrderDetails;
import com.store.migros.model.Product;
import com.store.migros.repository.OrderRepository;
import com.store.migros.repository.CustomerRepository;
import com.store.migros.repository.ProductRepository;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

	private final OrderRepository orderRepository;
	private final CustomerRepository customerRepository;
	private final ProductRepository productRepository;

	public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository,
			ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
	}

	@Override
	@Transactional
	public Order createOrder(Order order) {
		Customer customer = customerRepository.findById(order.getCustomer().getId())
				.orElseThrow(() -> new RuntimeException("Customer not found"));
		order.setCustomer(customer);

		order.setOrderDate(LocalDate.now());

		for (OrderDetails details : order.getOrderDetails()) {
			Product product = productRepository.findById(details.getProduct().getId())
					.orElseThrow(() -> new RuntimeException("Product not found"));

			if (product.getStock() < details.getQuantity()) {
				throw new RuntimeException("Can't find stock for product: " + product.getName());
			}

			product.setStock(product.getStock() - details.getQuantity());
			productRepository.save(product);

			details.setOrder(order);
			details.setProduct(product);
		}

		return orderRepository.save(order);
	}

	@Override
	@Transactional
	public Order updateOrder(Long id, Order order) {
		Order existing = getOrderById(id);
		existing.setOrderDate(order.getOrderDate());
		existing.setCustomer(order.getCustomer());
		return orderRepository.save(existing);
	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}
}
