package com.example.kamervankrypto.controller;


import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.model.Rate;
import com.example.kamervankrypto.service.AssetService;
import com.example.kamervankrypto.service.RateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/assets")
public class AssetController {

    private AssetService assetService;
    private RateService rateService;

    public AssetController(AssetService assetService, RateService rateService) {
        this.assetService = assetService;
        this.rateService = rateService;
    }

    @GetMapping
    List<Asset> getAll() {
        return assetService.getAll();
    }

    @GetMapping(value = "/rates")
    List<Asset> getAllWithCurrenRates() {
        return assetService.getAllWithCurrentRate();
    }


    @PostMapping //of Post? TODO
    List<Rate> createRate(@RequestBody Rate rate) {
        rateService.store(rate);
        return rateService.getAllByTicker(rate.getPair().getTicker());
    }

    @GetMapping(value = "/{ticker}")
    Asset getByTickerWithCurrentRate(@PathVariable("ticker") String ticker) {
        return assetService.getByTickerWithCurrentRate(ticker);
    }

    @GetMapping(value = "/rates/historical")
    List<Asset> getAllWithAll() {
        return assetService.getAllWithAllRates();
    }

    @GetMapping(value = "/find")
    Asset getByNameWithCurrentRate(@RequestParam("Name") String name) {
        return assetService.getByNameWithCurrentRate(name);
    }

    @PostMapping(value = "/add")
    List<Asset> createCustomer(@RequestBody Asset asset) {
        assetService.store(asset);
        return assetService.getAll();
    }
}

