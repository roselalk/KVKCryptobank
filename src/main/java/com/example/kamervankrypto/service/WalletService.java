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
        this.loggedInTrader = new Trader(1, "one@kvkrypto.nl", "1234",
                "Bob", "de", "Bank", 0, null, "streetName" ,
                "1", "1234AB", "Hilversum" , true);
    }

    public List<Wallet> getAllByTrader() {
        return walletRepository.findAllByTraderWithAssets(loggedInTrader);
    }

    public Optional<Wallet> getByTraderIdAndAssetTicker(String ticker) {
        return walletRepository.findByTraderAndTickerWithAsset(loggedInTrader, ticker);
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
