package com.example.kamervankrypto.dto;

import com.example.kamervankrypto.model.Rate;

public class RateDTO {

    double value;
    String date;
    String ticker;

    public RateDTO() {
    }

    public RateDTO(Rate r) {
        value = r.getValue();
        date = r.getDate();
        ticker = r.getAsset().getTicker();
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

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
