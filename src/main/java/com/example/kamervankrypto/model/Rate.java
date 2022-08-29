package com.example.kamervankrypto.model;

import java.util.Objects;

public class Rate {

    private int rateId;
    private double value;
    private String date;
    private Asset pair;


    public Rate(int rateId, double value, String date) {
        this.rateId = rateId;
        this.value = value;
        this.date = date;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Asset getPair() {
        return pair;
    }

    public void setPair(Asset pair) {
        this.pair = pair;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rate rate)) return false;
        return getRateId() == rate.getRateId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRateId());
    }

    @Override
    public String toString() {
        return "Rate{" +
                "value=" + value +
                ", date='" + date + '\'' +
                '}';
    }
}
