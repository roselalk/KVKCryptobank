package com.example.kamervankrypto.model;

import java.util.List;
import java.util.Objects;

public class Asset {

    private String ticker;
    private String name;
    private Rate rate;
    private List<Rate> historicalRates;

    public Asset(String ticker, String name) {
        this.ticker = ticker;
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public List<Rate> getHistoricalRates() {
        return historicalRates;
    }

    public void setHistoricalRates(List<Rate> historicalRates) {
        this.historicalRates = historicalRates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Asset asset)) return false;
        return getTicker().equals(asset.getTicker());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTicker());
    }

    @Override
    public String toString() {
        return "Asset{" +
                "ticker='" + ticker + '\'' +
                ", name='" + name + '\'' +
                ", rate=" + rate +
                '}';
    }
}
