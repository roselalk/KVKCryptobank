package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.model.Portfolio;
import com.example.kamervankrypto.model.Rate;
import com.example.kamervankrypto.model.Trader;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PortfolioRepository {
    private PortfolioDAO portfolioDAO;
    private RateDAO rateDAO;

    public PortfolioRepository(PortfolioDAO portfolioDAO, RateDAO rateDAO) {
        this.portfolioDAO = portfolioDAO;
        this.rateDAO = rateDAO;
    }

    public Portfolio findByTrader (Trader trader) {
        Portfolio portfolio = portfolioDAO.findByTraderId(trader.getID());
        portfolio.setTrader(trader);
        for (Asset a: portfolio.getAssets().keySet()) {
            List<Rate> rates = rateDAO.getAllByTicker(a.getTicker());
            for (Rate r : rates) {
                r.setAsset(a);
            }
            a.setHistoricalRates(rates);
        }
        return portfolio;
    }

    public Portfolio findWalletByTraderAndTicker(Trader trader, String ticker) {
        Portfolio portfolio = portfolioDAO.findWalletByTraderIdAndTicker(trader.getID(), ticker);
        portfolio.setTrader(trader);
        Map<Asset, Double> assets = portfolio.getAssets();
        assets.keySet().forEach(asset ->
                asset.setHistoricalRates(rateDAO.getAllByTicker(asset.getTicker())));
        portfolio.setAssets(assets);
        return portfolio;
    }

    public void createOrUpdate (Trader trader, String ticker, double amountToAddOrSubtract) {
        Portfolio portfolio = findWalletByTraderAndTicker(trader, ticker);
        if (portfolio.getAssets().isEmpty()) {
            portfolioDAO.create(trader.getID(), ticker, amountToAddOrSubtract);
        } else {
            //since we only have 1 wallet in the portfolio I'm calculating the sum to get the value
            //todo: think if there is a better way
            double previousAmount = portfolio.getAssets().values().stream().mapToDouble(Double::doubleValue).sum();
            portfolioDAO.update(trader.getID(), ticker, previousAmount + amountToAddOrSubtract);
        }
    }
}
