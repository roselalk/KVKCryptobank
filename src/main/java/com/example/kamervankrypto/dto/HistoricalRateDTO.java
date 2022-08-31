package com.example.kamervankrypto.dto;

import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.model.Rate;

public class HistoricalRateDTO {

    private double rate;
    private String date;

    public HistoricalRateDTO() {
    }

    public HistoricalRateDTO(Asset a) {
        rate = a.getRate().getValue();
        date = a.getRate().getDate();
    }

    public HistoricalRateDTO(Rate r) {
        rate = r.getValue();
        date = r.getDate();
    }

    public HistoricalRateDTO(HistoricalRateDTO historicalRateDTO) {
        rate = historicalRateDTO.getRate();
        date = historicalRateDTO.getDate();
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
