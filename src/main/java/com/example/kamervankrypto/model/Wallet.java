package com.example.kamervankrypto.model;

import java.util.Objects;

public class Wallet {
    int traderId;
    String ticker;
    double amount;

    public Wallet(String ticker, double amount) {
        this.ticker = ticker;
        this.amount = amount;
    }

    public Wallet(int traderId, String ticker, double amount) {
        this(ticker, amount);
        this.traderId = traderId;
    }

    public int getTraderId() {
        return traderId;
    }

    public void setTraderId(int traderId) {
        this.traderId = traderId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "traderId=" + traderId +
                ", ticker='" + ticker + '\'' +
                ", amount=" + amount +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wallet wallet)) return false;
        return traderId == wallet.traderId && Double.compare(wallet.amount, amount) == 0 && Objects.equals(ticker, wallet.ticker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(traderId, ticker, amount);
    }
}
