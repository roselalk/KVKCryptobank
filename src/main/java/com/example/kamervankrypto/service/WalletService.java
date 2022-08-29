package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.model.Wallet;
import com.example.kamervankrypto.repository.WalletDAO;
import com.example.kamervankrypto.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    private WalletDAO walletDAO;
    private WalletRepository walletRepository;
    public Trader loggedInTrader;

    public WalletService(WalletDAO walletDAO, WalletRepository walletRepository) {
        this.walletDAO = walletDAO;
        this.walletRepository = walletRepository;
        this.loggedInTrader = walletRepository.loggedInTrader;
    }

    public List<Wallet> getAllByTraderId(int id) {
        return walletRepository.findAllByTraderIdWithAssets(id);
    }

    public Optional<Wallet> getByTraderIdAndAssetTicker(int id, String ticker) {
        return walletRepository.findByTraderIdAndTickerWithAsset(id, ticker);
    }

    public void save(Wallet wallet) {
        walletDAO.save(wallet);
    }

    public void update(Wallet wallet) {
        walletDAO.update(wallet);
    }

    public void delete(Wallet wallet) {
        walletDAO.delete(wallet);
    }


}
