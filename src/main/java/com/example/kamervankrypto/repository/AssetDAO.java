package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Asset;

import java.util.List;

public interface AssetDAO {

    List<Asset> getAll();

    Asset findByTicker(String ticker);

    Asset findByName(String name);

    void save(Asset asset);

    void update(Asset asset);

}
