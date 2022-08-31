package com.example.kamervankrypto.dto;

import com.example.kamervankrypto.model.Asset;

public class AssetCurrentRateDTO {

    private String ticker;
    private String name;
    private String date;
    private double rate;

    public AssetCurrentRateDTO() {
    }

    public AssetCurrentRateDTO(Asset a){
        ticker = a.getTicker();
        name = a.getName();
        date = a.getRate().getDate();
        rate = a.getRate().getValue();
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
