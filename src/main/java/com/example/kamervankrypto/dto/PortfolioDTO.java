package com.example.kamervankrypto.dto;
import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.model.Portfolio;

import java.util.Map;


public class PortfolioDTO {
    private int traderId;
    private String totalValue;
    private Map<Asset, Double> assets;

    public PortfolioDTO() {}

    public PortfolioDTO(Portfolio portfolio) {
        traderId = portfolio.getTrader().getID();
        totalValue = "€ " + Math.round(portfolio.calculateTotalValue() * 100) / 100.0;
        assets = portfolio.getAssets();
    }

    public int getTraderId() {
        return traderId;
    }

    public void setTraderId(int traderId) {
        this.traderId = traderId;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public Map<Asset, Double> getAssets() {
        return assets;
    }

    public void setAssets(Map<Asset, Double> assets) {
        this.assets = assets;
    }
}
