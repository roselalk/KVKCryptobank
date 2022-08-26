package com.example.kamervankrypto.controller;


import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/assets")
public class AssetController {

    private AssetService assetService;

    @Autowired
    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    @ResponseBody
    List<Asset> getAssetsWithCurrentRate() {
        return assetService.getAllWithCurrentRate();
    }

    @GetMapping(value = "/ticker/{ticker}")
    Asset getAssetByTicker(@PathVariable("ticker") String ticker) {
        Optional<Asset> asset = Optional.ofNullable(assetService.getByTickerWithCurrentRate(ticker));
        if (asset.isPresent()) {
            return asset.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Asset not found!");
        }
    }

    @GetMapping(value = "/name/{name}")
    Asset getAssetByName(@PathVariable("name") String name) {
        Optional<Asset> asset = Optional.ofNullable(assetService.getByNameWithCurrentRate(name));
        if (asset.isPresent()) {
            return asset.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Asset not found!");
        }
    }

}
