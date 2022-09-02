package com.example.kamervankrypto.model;

import java.util.Objects;

public class Transaction {
    private int idTransaction;
    private double Amount1;
    private double transactionFee;
    private String transactionDateTime;
    private Trader buyer;
    private Trader seller;
    private Asset asset;

    //  Partial constructor, excludes objects of other classes.
    public Transaction(int idTransaction, double amount1, double transactionFee, String transactionDateTime) {
        this.idTransaction = idTransaction;
        this.Amount1 = amount1;
        this.transactionFee = transactionFee;
        this.transactionDateTime = transactionDateTime;
    }

    //  Getters and Setters.
    public int getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(int idTransaction) {
        this.idTransaction = idTransaction;
    }

    public double getAmount1() {
        return Amount1;
    }

    public void setAmount1(double amount1) {
        Amount1 = amount1;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }

    public Trader getBuyer() {
        return buyer;
    }

    public void setBuyer(Trader buyer) {
        this.buyer = buyer;
    }

    public Trader getSeller() {
        return seller;
    }

    public void setSeller(Trader seller) {
        this.seller = seller;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    public String getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    //  Equals and Hashcode methods.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return idTransaction == that.idTransaction && Double.compare(that.Amount1, Amount1) == 0 && Double.compare(that.transactionFee, transactionFee) == 0 && transactionDateTime.equals(that.transactionDateTime) && buyer.equals(that.buyer) && seller.equals(that.seller) && asset.equals(that.asset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransaction, Amount1, transactionFee, transactionDateTime, buyer, seller, asset);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "idTransaction=" + idTransaction +
                ", Amount1=" + Amount1 +
                ", transactionFee=" + transactionFee +
                ", transactionDateTime='" + transactionDateTime + '\'' +
                ", buyer=" + buyer +
                ", seller=" + seller +
                ", asset=" + asset +
                '}';
    }
}

