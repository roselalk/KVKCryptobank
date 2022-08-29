package com.example.kamervankrypto.model;
import java.util.Objects;

//A wallet object represents each cryptocurrency a specific trader possesses and the amount
//Each trader has a Portfolio list that contains ALL their wallet objects
public class Wallet {
    private int idWallet;
    private Trader trader;
    private Asset asset;
    private double amount;

    //CONSTRUCTOR:
    //this constructor we need for the WalletDAO
    public Wallet (int idWallet, double amount) {
        this.idWallet = idWallet;
        this.amount = amount;
    }

    //all args constructor just because
    //todo: delete if not needed
    public Wallet(int idWallet, double amount, Trader trader, Asset asset) {
        this(idWallet, amount);
        this.trader = trader;
        this.asset = asset;
    }

    //GETTERS AND SETTERS:
    public int getIdWallet() {
        return idWallet;
    }
    public void setIdWallet(int idWallet) {
        this.idWallet = idWallet;
    }
    public Trader getTrader() {
        return trader;
    }
    public void setTrader(Trader trader) {
        this.trader = trader;
    }
    public Asset getAsset() {
        return asset;
    }
    public void setAsset(Asset asset) {
        this.asset = asset;
    }
    public double getAmount() {
        return amount;
    }
    //todo: exception handling amount of asset has to be always > 0
    public void setAmount(double amount) {
        this.amount = amount;
    }

    //TO STRING
    //TODO: check if we need all attributes or only the ones from the constructor
    @Override
    public String toString() {
        return "Wallet{" +
                "idWallet=" + idWallet +
                ", trader=" + trader +
                ", asset=" + asset +
                ", amount=" + amount +
                '}';
    }

    //EQUALS AND HASHCODE:
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return idWallet == wallet.idWallet;
    }
    @Override
    public int hashCode() {
        return Objects.hash(idWallet);
    }
}
