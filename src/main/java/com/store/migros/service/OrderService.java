package com.store.migros.service;

import com.store.migros.dto.OrderDto;
import com.store.migros.dto.OrderDetailsDto;
import com.store.migros.mapper.OrderMapper;
import com.store.migros.model.*;
import com.store.migros.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderDetailsRepository orderDetailsRepository,
                        CustomerRepository customerRepository,
                        ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public OrderDto createOrder(OrderDto dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(dto.getOrderDate());

        Order savedOrder = orderRepository.save(order);

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (OrderDetailsDto odDto : dto.getOrderDetails()) {
            Product product = productRepository.findById(odDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            OrderDetails od = new OrderDetails();
            od.setOrder(savedOrder);
            od.setProduct(product);
            od.setQuantity(odDto.getQuantity());
            orderDetailsList.add(od);
        }

        orderDetailsRepository.saveAll(orderDetailsList);
        savedOrder.setOrderDetails(orderDetailsList);

        return OrderMapper.toDto(savedOrder);
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::toDto)
                .toList();
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return OrderMapper.toDto(order);
    }

    public OrderDto updateOrder(Long id, OrderDto dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (dto.getCustomerId() != null) {
            Customer customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            order.setCustomer(customer);
        }

        if (dto.getOrderDate() != null) {
            order.setOrderDate(dto.getOrderDate());
        }

        if (dto.getOrderDetails() != null) {
            orderDetailsRepository.deleteAll(order.getOrderDetails());

            List<OrderDetails> newDetails = new ArrayList<>();
            for (OrderDetailsDto odDto : dto.getOrderDetails()) {
                Product product = productRepository.findById(odDto.getProductId())
                        .orElseThrow(() -> new RuntimeException("Product not found"));
                OrderDetails od = new OrderDetails();
                od.setOrder(order);
                od.setProduct(product);
                od.setQuantity(odDto.getQuantity());
                newDetails.add(od);
            }

            orderDetailsRepository.saveAll(newDetails);
            order.setOrderDetails(newDetails);
        }

        order = orderRepository.save(order);
        return OrderMapper.toDto(order);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderDetailsRepository.deleteAll(order.getOrderDetails());
        orderRepository.delete(order);
    }
}
