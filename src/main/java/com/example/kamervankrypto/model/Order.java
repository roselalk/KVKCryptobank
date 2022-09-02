package com.example.kamervankrypto.model;

import java.util.Objects;

abstract class Order {
    private int idOrder;
    private Trader trader;
    private double amount;
    private double rateThreshold;
    private Asset asset;
    // Logic should include method to prefer trader2trader transaction if available, else default to bank.
    private boolean tradeWithBank;

    //  All args constructor
    public Order(int idOrder, Trader trader, double amount, double rateThreshold, Asset asset, boolean tradeWithBank) {
        this.idOrder = idOrder;
        this.trader = trader;
        this.amount = amount;
        this.rateThreshold = rateThreshold;
        this.asset = asset;
        this.tradeWithBank = tradeWithBank;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRateThreshold() {
        return rateThreshold;
    }

    public void setRateThreshold(double rateThreshold) {
        this.rateThreshold = rateThreshold;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public boolean isTradeWithBank() {
        return tradeWithBank;
    }

    public void setTradeWithBank(boolean tradeWithBank) {
        this.tradeWithBank = tradeWithBank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return idOrder == order.idOrder && Double.compare(order.amount, amount) == 0 && Double.compare(order.rateThreshold, rateThreshold) == 0 && tradeWithBank == order.tradeWithBank && trader.equals(order.trader) && asset.equals(order.asset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder);
    }
}
