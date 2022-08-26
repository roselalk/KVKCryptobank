package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.repository.AssetDAO;
import com.example.kamervankrypto.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    private AssetRepository assetRepository;
    private AssetDAO assetDAO;

    public AssetService(AssetRepository assetRepository, AssetDAO assetDAO) {
        this.assetRepository = assetRepository;
        this.assetDAO = assetDAO;
    }

    public List<Asset> getAll() {
        return assetDAO.getAll();
    }

    public List<Asset> getAllWithCurrentRate() {
        return assetRepository.getAllWithCurrentRate();
    }

    public Asset getByTickerWithCurrentRate(String ticker) {
        return assetRepository.getByTickerWithCurrentRate(ticker);
    }

    public Asset getByNameWithCurrentRate(String name) {
        return assetRepository.getByNameWithCurrentRate(name);
    }

}
