package com.example.kamervankrypto.controller;

import com.example.kamervankrypto.model.Portfolio;
import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/portfolio/")
public class PortfolioController {
    private PortfolioService portfolioService;

    @Autowired
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping(value = "/assets")
    @ResponseBody
    Portfolio getPortfolioByTrader(@RequestBody Trader trader) {
        return portfolioService.getByTrader(trader);
    }

    @GetMapping(value = "/assets/{ticker}")
    @ResponseBody
    Portfolio getWalletByTraderAndTicker(@RequestBody Trader trader, @PathVariable("ticker") String ticker) {
        return portfolioService.getWalletByTraderAndTicker(trader, ticker);
    }

    @PostMapping (value = "/update/{ticker}/{amountToAddOrSubtract}")
    @ResponseBody
    Portfolio updateWallet(@RequestBody Trader trader, @PathVariable("ticker") String ticker, @PathVariable("amountToAddOrSubtract") double amount) {
        portfolioService.createOrUpdate(trader, ticker, amount);
        return portfolioService.getByTrader(trader);
    }

    @DeleteMapping ("/delete/{ticker}")
    public Portfolio deleteWallet(@RequestBody Trader trader, @PathVariable(value = "ticker") String ticker) {
        portfolioService.delete(trader, ticker);
        return portfolioService.getByTrader(trader);
    }

}


