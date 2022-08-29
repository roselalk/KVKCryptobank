package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.model.Wallet;
import com.example.kamervankrypto.repository.WalletDAO;
import com.example.kamervankrypto.repository.WalletRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    private WalletDAO walletDAO;
    private WalletRepository walletRepository;

    public WalletService(WalletDAO walletDAO, WalletRepository walletRepository) {
        this.walletDAO = walletDAO;
        this.walletRepository = walletRepository;
    }

    public List<Wallet> getAllByTrader(Trader trader) {
        return walletRepository.findAllByTraderWithAssets(trader);
    }

    public Optional<Wallet> getByTraderIdAndAssetTicker(Trader trader, String ticker) {
        return walletRepository.findByTraderAndTickerWithAsset(trader, ticker);
    }

    public void save(Trader trader, String ticker, double amount) {
        walletRepository.save(trader, ticker, amount);
    }

    public void update(int idWallet, double amount) {
        walletDAO.update(idWallet, amount);
    }

    public Wallet delete(Trader trader, String ticker) {
        Optional<Wallet> wallet = getByTraderIdAndAssetTicker(trader, ticker);
        if (wallet.isPresent()) {
            walletDAO.delete(wallet.get().getIdWallet());
            return wallet.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such wallet found!");
        }
    }


}
