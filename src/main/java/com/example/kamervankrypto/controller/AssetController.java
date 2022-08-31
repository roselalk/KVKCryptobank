package com.example.kamervankrypto.controller;


import com.example.kamervankrypto.dto.AssetCurrentRateDTO;
import com.example.kamervankrypto.dto.AssetHistoricalRatesDTO;
import com.example.kamervankrypto.dto.HistoricalRateDTO;
import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.service.AssetService;
import com.example.kamervankrypto.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    List<AssetCurrentRateDTO> getAllWithCurrenRates() {
        return assetService.getAllWithCurrentRate().stream().map(AssetCurrentRateDTO::new).toList();
    }

    @GetMapping(value = "/{ticker}")
    AssetCurrentRateDTO getByTickerWithCurrentRate(@PathVariable("ticker") String ticker) {
        return new AssetCurrentRateDTO(assetService.getByTickerWithCurrentRate(ticker));
    }

    //TODO
    //retrieval of historical rates still needs work with DTO
    @GetMapping(value = "/historical")
    List<Asset> getAllWithAll() {
        return assetService.getAllWithAllRates();
    }

    //TODO
    //retrieval of historical rates still needs work with DTO
    @GetMapping(value = "/historical/{ticker}")
    AssetHistoricalRatesDTO getByTickerWithHistorical(@PathVariable("ticker") String ticker) {
        AssetHistoricalRatesDTO assetHistoricalRatesDTO = new AssetHistoricalRatesDTO(assetService.getByTickerWithHistoricalRates(ticker));
        assetHistoricalRatesDTO.setHistoricalRates(assetHistoricalRatesDTO.getHistoricalRates().stream().map(HistoricalRateDTO::new).toList());
        return assetHistoricalRatesDTO;
    }

    @GetMapping(value = "/find")
    AssetCurrentRateDTO getByNameWithCurrentRate(@RequestParam("Name") String name) {
        return new AssetCurrentRateDTO(assetService.getByNameWithCurrentRate(name));
    }

    @PostMapping(value = "/add")
    List<Asset> createAsset(@RequestBody Asset asset) {
        assetService.store(asset);
        return assetService.getAll();
    }


}

