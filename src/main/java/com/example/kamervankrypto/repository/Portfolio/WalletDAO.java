package com.example.kamervankrypto.repository.Portfolio;
import com.example.kamervankrypto.model.Wallet;

import java.util.List;

public interface WalletDAO {
    List<Wallet> findAllWalletsByTraderId(int traderId);

    Wallet findWalletByTraderIdAndTicker(int traderId, String ticker);

    void updateWallet(int traderId, String ticker, double amount);

    void createWallet(int traderId, String ticker, double amount);

    void deleteWallet(int traderId, String ticker);
}