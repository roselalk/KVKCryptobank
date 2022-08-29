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

    //TODO: PLACE THIS SOMEWHERE ELSE WHERE WE CAN ALL USE IT AND EXTRACT IT FROM THE TOKEN
    public Trader loggedInTrader = new Trader(1, "one@kvkrypto.nl", "1234",
            "Bob", "de", "Bank", 0, null, "streetName" ,
            "1", "1234AB", "Hilversum" , true);

    public WalletRepository(WalletDAO walletDAO, AssetDAO assetDAO) {
        this.walletDAO = walletDAO;
        this.assetDAO = assetDAO;
    }

    public List<Wallet> findAllByTraderIdWithAssets(int id) {
        List<Wallet> walletList = walletDAO.findAllByTraderId(id);
        for (Wallet w : walletList) {
            w.setTrader(loggedInTrader);
            w.setAsset(assetDAO.findByTicker(w.getAsset().getTicker()));
        }
        //todo: when we get the wallets for the user who logged in, we set it on the portfolio object (do the same with bank account)
        // loggedInTrader.setPortfolio(walletList);
        return walletList;
    }

    public Optional<Wallet> findByTraderIdAndTickerWithAsset(int id, String ticker) {
        Optional<Wallet> wallet = walletDAO.findByTraderIdAndAsset(id, ticker);
        if (wallet.isPresent()) {
            wallet.get().setTrader(loggedInTrader);
            wallet.get().setAsset(assetDAO.findByTicker(ticker));
        }
        return wallet;
    }

}
