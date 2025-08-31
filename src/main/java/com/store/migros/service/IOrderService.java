package com.store.migros.service;

import com.store.migros.model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order createOrder(Order order);
    Order updateOrder(Long id, Order order);
    void deleteOrder(Long id);
}
