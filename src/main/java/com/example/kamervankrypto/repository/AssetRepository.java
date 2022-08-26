package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Asset;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssetRepository {

    private AssetDAO assetDAO;
    private RateDAO rateDAO;

    public AssetRepository(AssetDAO assetDAO, RateDAO rateDAO) {
        this.assetDAO = assetDAO;
        this.rateDAO = rateDAO;
    }

    public List<Asset> getAllWithCurrentRate() {
        List<Asset> assetList = assetDAO.getAll();
        for (Asset a : assetList) {
            a.setRate(rateDAO.getCurrentByTicker(a.getTicker()));
        }
        return assetList;
    }

    public Asset getByTickerWithCurrentRate(String ticker) {
        Asset a = assetDAO.getByTicker(ticker);
        a.setRate(rateDAO.getCurrentByTicker(a.getTicker()));
        return a;
    }

    public Asset getByNameWithCurrentRate(String name) {
        Asset a = assetDAO.getByName(name);
        a.setRate(rateDAO.getCurrentByTicker(a.getTicker()));
        return a;
    }
}
