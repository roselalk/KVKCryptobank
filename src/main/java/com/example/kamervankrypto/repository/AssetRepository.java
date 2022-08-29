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

    public List<Asset> getAll() {
        return assetDAO.getAll();
    }

    public List<Asset> getAllWithCurrentRate() {
        List<Asset> assetList = assetDAO.getAll();
        for (Asset a : assetList) {
            a.setValuedAt(rateDAO.getCurrentByTicker(a.getTicker()));
        }
        return assetList;
    }

    public Asset getByTickerWithCurrentRate(String ticker) {
        Asset a = assetDAO.getByTicker(ticker);
        a.setValuedAt(rateDAO.getCurrentByTicker(a.getTicker()));
        return a;
    }

    public Asset getByNameWithCurrentRate(String name) {
        Asset a = assetDAO.getByName(name);
        a.setValuedAt(rateDAO.getCurrentByTicker(a.getTicker()));
        return a;
    }

    public Asset getByTickerWithHistoricalRates(String ticker) {
        Asset a = assetDAO.getByTicker(ticker);
        a.setHistoricalRates(rateDAO.getAllByTicker(a.getTicker()));
        return a;
    }

    public Asset getByTickerWithAllRates(String ticker) {
        Asset a = assetDAO.getByTicker(ticker);
        a.setValuedAt(rateDAO.getCurrentByTicker(a.getTicker()));
        a.setHistoricalRates(rateDAO.getAllByTicker(a.getTicker()));
        return a;
    }

    public List<Asset> getAllWithAllRates() {
        List<Asset> assetList = assetDAO.getAll();
        for (Asset a : assetList) {
            a.setValuedAt(rateDAO.getCurrentByTicker(a.getTicker()));
            a.setHistoricalRates(rateDAO.getAllByTicker(a.getTicker()));
            a.getValuedAt().setTicker(a.getTicker());
        }
        return assetList;
    }
}
