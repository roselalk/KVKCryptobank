package com.example.kamervankrypto.model;

public class Rate {

    int idRate;
    double valuedAt;
    String date;
    String ticker;

    public Rate(int idRate, double value, String date) {
        this.idRate = idRate;
        this.valuedAt = value;
        this.date = date;
    }

    public int getIdRate() {
        return idRate;
    }

    public void setIdRate(int idRate) {
        this.idRate = idRate;
    }

    public double getValuedAt() {
        return valuedAt;
    }

    public void setValuedAt(double valuedAt) {
        this.valuedAt = valuedAt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }



}
