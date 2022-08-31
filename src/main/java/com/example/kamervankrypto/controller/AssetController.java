package com.example.kamervankrypto.controller;


import com.example.kamervankrypto.dto.AssetDTO;
import com.example.kamervankrypto.dto.RateDTO;
import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.service.AssetService;
import com.example.kamervankrypto.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/markets")
public class AssetController {

    private AssetService assetService;
    private RateService rateService;

    @Autowired
    public AssetController(AssetService assetService, RateService rateService) {
        this.assetService = assetService;
        this.rateService = rateService;
    }

    @GetMapping
    List<AssetDTO> getAllWithCurrenRates() {
        return assetService.getAllWithCurrentRate().stream().map(AssetDTO::new).toList();
    }

    @GetMapping(value = "/{ticker}")
    AssetDTO getByTickerWithCurrentRate(@PathVariable("ticker") String ticker) {
        return new AssetDTO(assetService.getByTickerWithCurrentRate(ticker));
    }

    @GetMapping(value = "/historical")
    List<RateDTO> getAllWithAll() {
        List<Asset> assetList = assetService.getAllWithAllRates();
        List<RateDTO> allRates = new ArrayList<>();
        for (Asset a : assetList) {
            a.getHistoricalRates().stream().map(RateDTO::new).forEach(allRates::add);
        }
        return allRates;
    }

    @GetMapping(value = "/historical/{ticker}")
    List<RateDTO> getByTickerWithHistorical(@PathVariable("ticker") String ticker) {
        Asset a = assetService.getByTickerWithHistoricalRates(ticker);
        return a.getHistoricalRates().stream().map(RateDTO::new).toList();
    }

    @GetMapping(value = "/find")
    AssetDTO getByNameWithCurrentRate(@RequestParam("Name") String name) {
        return new AssetDTO(assetService.getByNameWithCurrentRate(name));
    }

    @PostMapping(value = "/add")
    List<Asset> createAsset(@RequestBody Asset asset) {
        assetService.store(asset);
        return assetService.getAll();
    }


}

