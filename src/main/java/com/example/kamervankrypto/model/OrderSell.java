package com.example.kamervankrypto.model;

public class OrderSell extends Order {
    public OrderSell(int idOrder, Trader trader, double amount, double rateThreshold, Asset asset, boolean tradeWithBank) {
        super(idOrder, trader, amount, rateThreshold, asset, tradeWithBank);
    }
}
