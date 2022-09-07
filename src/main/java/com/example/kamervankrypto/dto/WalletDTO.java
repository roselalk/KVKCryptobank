package com.example.kamervankrypto.dto;

import com.example.kamervankrypto.model.Wallet;

public class WalletDTO {
    private int traderId;
    private String asset;
    private double amount;

    public WalletDTO () {}

    public WalletDTO (Wallet wallet) {
        this.traderId = wallet.getTraderId();
        this.asset = wallet.getTicker();
        this.amount = wallet.getAmount();
    }

    public int getTraderId() {
        return traderId;
    }

    public void setTraderId(int traderId) {
        this.traderId = traderId;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
