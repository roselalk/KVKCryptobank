package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.Portfolio;
import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.model.Wallet;
import com.example.kamervankrypto.repository.Portfolio.PortfolioRepository;
import com.example.kamervankrypto.repository.Portfolio.WalletDAO;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService {
    WalletDAO walletDAO;
    PortfolioRepository portfolioRepository;

    public PortfolioService(WalletDAO walletDAO, PortfolioRepository portfolioRepository) {
        this.walletDAO = walletDAO;
        this.portfolioRepository = portfolioRepository;
    }

    public Portfolio getByTrader (Trader trader) {
        return portfolioRepository.findByTrader(trader);
    }

    public Wallet getWalletByTraderAndTicker(Trader trader, String ticker) {
        return portfolioRepository.findWalletByTraderAndTicker(trader, ticker);
    }

    public void createOrUpdate(Trader trader, String ticker, double amountToAddOrSubtract) {
        portfolioRepository.createOrUpdate(trader, ticker, amountToAddOrSubtract);
    }

    public void delete(Trader trader, String ticker) {
        walletDAO.deleteWallet(trader.getID(), ticker);
    }
}
