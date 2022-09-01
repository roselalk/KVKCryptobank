package com.example.kamervankrypto.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class PortfolioTest {
    Portfolio portfolio;
    Asset asset1;
    Asset asset2;

    @BeforeEach
    public void portfolioSetup(){

        asset1 = new Asset("A1", "Asset1");
        Rate rate1a1 = new Rate(2.2, "date");
        Rate rate2a1 = new Rate( 10, "date");
        List<Rate> historicalRates1 = new ArrayList<>();
        historicalRates1.add(rate1a1);
        historicalRates1.add(rate2a1);
        asset1.setHistoricalRates(historicalRates1);

        asset2 = new Asset("A2", "Asset2");
        Rate rate1a2 = new Rate( 7, "date");
        Rate rate2a2 = new Rate( 0.5, "date");
        List<Rate> historicalRates2 = new ArrayList<>();
        historicalRates2.add(rate1a2);
        historicalRates2.add(rate2a2);
        asset2.setHistoricalRates(historicalRates2);

        Map<Asset, Double> assetDoubleMap = new TreeMap<>();
        assetDoubleMap.put(asset1, 500.0);
        assetDoubleMap.put(asset2, 100.0);

        portfolio = new Portfolio(assetDoubleMap);
    }

    @Test
    void calculate_value_assets_in_euros_test_1() {
        Map<Asset, Double> actual = portfolio.calculateValueAssetsInEuros();
        Map<Asset, Double> expected = new TreeMap<>();
        expected.put(asset1, 1100.0);
        expected.put(asset2, 700.0);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculate_TotalValue_Test() {
        double actual = portfolio.calculateTotalValue();
        double expected = 1800.0;
        assertThat(actual).isEqualTo(expected);
    }
}