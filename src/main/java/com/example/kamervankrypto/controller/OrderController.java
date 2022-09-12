package com.example.kamervankrypto.controller;

import com.example.kamervankrypto.model.OrderBuy;
import com.example.kamervankrypto.model.OrderSell;
import com.example.kamervankrypto.service.OrderService;
import com.example.kamervankrypto.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class OrderController {

    private OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @ResponseBody
    List<OrderSell> getAllSellOrders() {
        return orderService.getAllSellOrders();
    }

    @GetMapping
    @ResponseBody
    List<OrderBuy> getAllBuyOrders() {
        return orderService.getAllBuyOrders();
    }


}
