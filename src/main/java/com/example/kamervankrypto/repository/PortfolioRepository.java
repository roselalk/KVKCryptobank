package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.model.Portfolio;
import com.example.kamervankrypto.model.Rate;
import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.service.AssetService;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PortfolioRepository {
    private PortfolioDAO portfolioDAO;
    private RateDAO rateDAO;

    public PortfolioRepository(PortfolioDAO portfolioDAO, RateDAO rateDAO, AssetService assetService) {
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
        portfolio.getAssets().keySet().forEach(asset ->
                asset.setHistoricalRates(rateDAO.getAllByTicker(asset.getTicker())));
        return portfolio;
    }

    public void createOrUpdate (Trader trader, String ticker, double amount) {
        Portfolio portfolio = portfolioDAO.findWalletByTraderIdAndTicker(trader.getID(), ticker);
        if (portfolio.getAssets().isEmpty()) {
            portfolioDAO.create(trader.getID(), ticker, amount);
        } else {
            portfolioDAO.update(trader.getID(), ticker, amount);
        }
    }
}
