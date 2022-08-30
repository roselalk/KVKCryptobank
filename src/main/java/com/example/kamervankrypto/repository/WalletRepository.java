package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.model.Wallet;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WalletRepository {
    private WalletDAO walletDAO;
    private AssetDAO assetDAO;
    public WalletRepository(WalletDAO walletDAO, AssetDAO assetDAO) {
        this.walletDAO = walletDAO;
        this.assetDAO = assetDAO;
    }

    public List<Wallet> findAllByTraderWithAssets(Trader trader) {
        List<Wallet> walletList = walletDAO.findAllByTraderId(trader.getID());
        for (Wallet w : walletList) {
            w.setTrader(trader);
        }
        return walletList;
    }

    public Optional<Wallet> findByTraderAndTickerWithAsset(Trader trader, String ticker) {
        Optional<Wallet> wallet = walletDAO.findByTraderIdAndAsset(trader.getID(), ticker);
        if (wallet.isPresent()) {
            wallet.get().setTrader(trader);
            wallet.get().setAsset(assetDAO.getByTicker(ticker));
        }
        return wallet;
    }

    public void save(Trader trader, String ticker, double amount) {
        Wallet wallet = new Wallet(trader, assetDAO.getByTicker(ticker), amount);
        walletDAO.save(wallet);
    }



}
