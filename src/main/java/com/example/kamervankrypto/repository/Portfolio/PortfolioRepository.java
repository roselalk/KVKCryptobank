package com.example.kamervankrypto.repository.Portfolio;

import com.example.kamervankrypto.model.*;
import com.example.kamervankrypto.repository.Asset.AssetRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class PortfolioRepository {
    private WalletDAO walletDAO;
    private AssetRepository assetRepository;

    public PortfolioRepository(WalletDAO walletDAO, AssetRepository assetRepository) {
        this.walletDAO = walletDAO;
        this.assetRepository = assetRepository;
    }

    public Portfolio findByTrader (Trader trader) {
        List<Wallet> wallets = walletDAO.findAllWalletsByTraderId(trader.getID());
        Map<Asset, Double> assets = new TreeMap<>();
        wallets.forEach(wallet -> assets.put(assetRepository.getByTickerWithCurrentRate(wallet.getTicker()), wallet.getAmount()));
        return new Portfolio(trader, assets);
    }

    public Wallet findWalletByTraderAndTicker(Trader trader, String ticker) {
        return walletDAO.findWalletByTraderIdAndTicker(trader.getID(), ticker);
    }

    public void createOrUpdate (Trader trader, String ticker, double amountToAddOrSubtract) {
        Wallet wallet = walletDAO.findWalletByTraderIdAndTicker(trader.getID(), ticker);
        if (wallet == null) { //if the trader doesn't own the asset
            if (amountToAddOrSubtract > 0) {
                walletDAO.createWallet(trader.getID(), ticker, amountToAddOrSubtract);
            } else {
                throw new RuntimeException("Can't subtract because trader doesn't own this asset");
            }
        } else {
            double amount = wallet.getAmount() + amountToAddOrSubtract;
            if (amount > 0) { //add to an existing asset or subtract less than the total amount
                walletDAO.updateWallet(trader.getID(), ticker, amount);
            } else if (amount == 0) { //subtract the total amount
                walletDAO.deleteWallet(trader.getID(), ticker);
            } else {
                throw new RuntimeException("Can't subtract because the trader doesn't own enough of this asset");
            }
        }
    }
}
