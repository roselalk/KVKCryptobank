package com.example.kamervankrypto.model;

import java.util.Objects;

public class Rate implements Comparable<Rate>{
    private double value;
    private String date;
    private Asset asset;

    public Rate(double value, String date) {
        this.value = value;
        this.date = date;
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

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rate rate)) return false;
        return Double.compare(rate.getValue(), getValue()) == 0 && getDate().equals(rate.getDate()) && Objects.equals(getAsset(), rate.getAsset());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getDate(), getAsset());
    }

    @Override
    public int compareTo(Rate o) {
        return this.date.compareTo(o.date);
    }
}
