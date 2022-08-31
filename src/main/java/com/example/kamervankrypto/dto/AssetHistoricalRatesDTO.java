package com.example.kamervankrypto.dto;

import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.model.Rate;

import java.util.List;

public class AssetHistoricalRatesDTO {

    private String ticker;
    private String name;
    private List<HistoricalRateDTO> historicalRates;

    public AssetHistoricalRatesDTO() {
    }

    public AssetHistoricalRatesDTO(Asset a) {
        ticker = a.getTicker();
        name = a.getName();
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

    public List<HistoricalRateDTO> getHistoricalRates() {
        return historicalRates;
    }

    public void setHistoricalRates(List<HistoricalRateDTO> historicalRates) {
        this.historicalRates = historicalRates;
    }
}

