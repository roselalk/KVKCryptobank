package com.example.kamervankrypto.repository.Portfolio;

import com.example.kamervankrypto.model.Portfolio;

public interface PortfolioDAO {
    Portfolio findByTraderId (int traderId);

    Portfolio findWalletByTraderIdAndTicker (int traderId, String ticker);

    void update (int traderId, String ticker, double amount);

    void create (int traderId, String ticker, double amount);

    void delete (int traderId, String ticker);

}
