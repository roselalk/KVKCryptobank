package com.example.kamervankrypto.controller;


import com.example.kamervankrypto.model.Rate;
import com.example.kamervankrypto.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping(value = "/rates")
public class RateController {

    private RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @PostMapping(value = "rates/add")
    List<Rate> createCustomer(@RequestBody Rate rate) {
        rateService.store(rate);
        return rateService.getAllByTicker(rate.getPair().getTicker());
    }


}
