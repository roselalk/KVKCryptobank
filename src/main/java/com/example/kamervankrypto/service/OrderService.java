package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.OrderBuy;
import com.example.kamervankrypto.model.OrderSell;
import com.example.kamervankrypto.repository.Order.OrderDAO;
import com.example.kamervankrypto.repository.Order.OrderRepository;

import java.util.List;

public class OrderService {

    private OrderDAO orderDAO;
    private OrderRepository orderRepository;

    public OrderService(OrderDAO orderDAO, OrderRepository orderRepository) {
        this.orderDAO = orderDAO;
        this.orderRepository = orderRepository;
    }

    public List<OrderSell> getAllSellOrders() {
        return null;
    }

    public List<OrderBuy> getAllBuyOrders() {
        return null;
    }
}
