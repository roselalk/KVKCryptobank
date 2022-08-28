package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Asset;

import java.util.List;
import java.util.Optional;

public interface AssetDAO {

    List<Asset> getAll();

    Asset getByTicker(String ticker);

    Asset getByName(String name);
}
