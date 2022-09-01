package com.example.kamervankrypto.model;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Portfolio {
    private Trader trader;
    private Map<Asset, Double> assets;

    public Portfolio(Trader trader, Map<Asset, Double> assets) {
        this.trader = trader;
        this.assets = assets;
    }

    public Portfolio(Trader trader) {
        this.trader = trader;
    }

    public Portfolio(Map<Asset, Double> assets) {
        this.assets = assets;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "trader=" + trader.getID() +
                ", assets=" + assets +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Portfolio portfolio)) return false;
        return trader.equals(portfolio.trader) && assets.equals(portfolio.assets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trader, assets);
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public Map<Asset, Double> getAssets() {
        return assets;
    }

    public void setAssets(Map<Asset, Double> assets) {
        this.assets = assets;
    }

    public Map<Asset, Double> calculateValueAssetsInEuros() {
        Map<Asset, Double> mapInEuros = new TreeMap<>();
        assets.forEach((asset, amount) -> mapInEuros.put(asset, amount * asset.getHistoricalRates().get(0).getValue()));
        return mapInEuros;
    }

    public double calculateTotalValue() {
        return calculateValueAssetsInEuros().values().stream().mapToDouble(Double::doubleValue).sum();
    }
}
