package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.repository.AssetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetService {

    private AssetRepository assetRepository;

    public AssetService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<Asset> getAll(){
        return assetRepository.getAll();
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
