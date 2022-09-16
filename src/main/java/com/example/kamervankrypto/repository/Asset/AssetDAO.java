package com.example.kamervankrypto.repository.Asset;

import com.example.kamervankrypto.model.Asset;

import java.util.List;

public interface AssetDAO {

    List<Asset> getAll();

    Asset getByTicker(String ticker);

    Asset getByName(String name);

    void save(Asset asset);

    void delete(Asset asset);

    void update(Asset asset);
}
