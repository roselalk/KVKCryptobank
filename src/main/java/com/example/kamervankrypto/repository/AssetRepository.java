package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.model.Rate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Repository
public class AssetRepository {

    private AssetDAO assetDAO;
    private RateDAO rateDAO;

    public AssetRepository(AssetDAO assetDAO, RateDAO rateDAO) {
        this.assetDAO = assetDAO;
        this.rateDAO = rateDAO;
    }

    public List<Asset> getAll() {
        return assetDAO.getAll();
    }

    public List<Asset> getAllWithCurrentRate() {
        List<Asset> assetList = assetDAO.getAll();
        for (Asset a : assetList) {
            Rate r = rateDAO.getCurrentByTicker(a.getTicker());
            if (r != null) {
                a.setRate(r);
                r.setAsset(a);
            }
        }
        return assetList;
    }

    public Asset getByTickerWithCurrentRate(String ticker) {
        Asset a = assetDAO.getByTicker(ticker);
        if (a != null) {
            Rate r = rateDAO.getCurrentByTicker(ticker);
            a.setRate(r);
            r.setAsset(a);
            return a;
        }
        return null;
    }

    public Asset getByNameWithCurrentRate(String name) {
        Asset a = assetDAO.getByName(name);
        a.setRate(rateDAO.getCurrentByTicker(a.getTicker()));
        return a;
    }

    public Asset getByTickerWithHistoricalRates(String ticker) {
        Asset a = assetDAO.getByTicker(ticker);
        List<Rate> rates = rateDAO.getAllByTicker(ticker);
        for (Rate r : rates) {
            r.setAsset(a);
        }
        a.setHistoricalRates(rates);
        return a;
    }

    public Asset getByTickerWithAllRates(String ticker) {
        Asset a = assetDAO.getByTicker(ticker);
        a.setRate(rateDAO.getCurrentByTicker(a.getTicker()));
        a.setHistoricalRates(rateDAO.getAllByTicker(a.getTicker()));
        return a;
    }

    public List<Asset> getAllWithAllRates() {
        List<Asset> assetList = assetDAO.getAll();
        if (!assetList.isEmpty()) {
            for (Asset a : assetList) {
                List<Rate> rateList = rateDAO.getAllByTicker(a.getTicker());
                for (Rate r : rateList) {
                    r.setAsset(a);
                }
                a.setRate(rateDAO.getCurrentByTicker(a.getTicker()));
                a.setHistoricalRates(rateDAO.getAllByTicker(a.getTicker()));
            }
            return assetList;
        }
        return Collections.emptyList();
    }

    public void store(Asset asset) {
        assetDAO.save(asset);
    }
}
