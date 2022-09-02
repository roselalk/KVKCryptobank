package com.example.kamervankrypto.model;

public class OrderBuy extends Order{
    public OrderBuy(int idOrder, Trader trader, double amount, double rateThreshold, Asset asset, boolean tradeWithBank) {
        super(idOrder, trader, amount, rateThreshold, asset, tradeWithBank);
    }
}
