package com.example.kamervankrypto.repository;
import com.example.kamervankrypto.model.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletDAO {
    List<Wallet> findAllByTraderId(int traderId);
    Optional<Wallet> findByTraderIdAndAsset(int id, String ticker);
    void save(Wallet wallet);
    void update(Wallet wallet);
    void delete(Wallet wallet);
    /*
    todo: do we need a method to bring all wallets with the same asset from all traders??
    possible solution: we use findByTraderIdAndAsset in the TraderService?
     */
}
