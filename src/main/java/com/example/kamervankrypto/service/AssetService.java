package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.repository.AssetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    private AssetDAO assetDAO;

    @Autowired
    public AssetService(AssetDAO assetDAO) {
        this.assetDAO = assetDAO;
    }

    public List<Asset> getAll() {
        return assetDAO.getAll();
    }

    public Asset getByTicker(String ticker) {
        return assetDAO.findByTicker(ticker);
    }

    public Asset getByName(String name) {
        return assetDAO.findByName(name);
    }

}
