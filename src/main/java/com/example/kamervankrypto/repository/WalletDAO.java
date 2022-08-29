package com.example.kamervankrypto.repository;
import com.example.kamervankrypto.model.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletDAO {
    List<Wallet> findAllByTraderId(int traderId);
    Optional<Wallet> findByTraderIdAndAsset(int id, String ticker);
    void save(Wallet wallet);
    void update(int idWallet, double amount);
    void delete(int idWallet);

}
