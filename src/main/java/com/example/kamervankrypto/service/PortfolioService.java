package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.Portfolio;
import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.repository.PortfolioDAO;
import com.example.kamervankrypto.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {
    PortfolioDAO portfolioDAO;
    PortfolioRepository portfolioRepository;

    public PortfolioService(PortfolioDAO portfolioDAO, PortfolioRepository portfolioRepository) {
        this.portfolioDAO = portfolioDAO;
        this.portfolioRepository = portfolioRepository;
    }

    public Portfolio getByTrader (Trader trader) {
        return portfolioRepository.findByTrader(trader);
    }

    public Portfolio getWalletByTraderAndTicker(Trader trader, String ticker) {
        return portfolioRepository.findWalletByTraderAndTicker(trader, ticker);
    }

    public void createOrUpdate(Trader trader, String ticker, double amount) {
        portfolioRepository.createOrUpdate(trader, ticker, amount);
    }

    public void delete(Trader trader, String ticker) {
        portfolioDAO.delete(trader.getID(), ticker);
    }
}
