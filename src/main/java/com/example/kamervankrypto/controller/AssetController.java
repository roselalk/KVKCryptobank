package com.example.kamervankrypto.controller;


import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.model.Rate;
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
    List<Asset> getAll() {
        return assetService.getAll();
    }

    @GetMapping(value = "/rates")
    List<Asset> getAllWithCurrenRates() {
        return assetService.getAllWithCurrentRate();
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
    @ResponseBody
    Asset getByNameWithCurrentRate(@RequestParam("Name") String name) {
        return assetService.getByNameWithCurrentRate(name);
    }

}

