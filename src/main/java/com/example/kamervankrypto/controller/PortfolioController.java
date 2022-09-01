package com.example.kamervankrypto.controller;

import com.example.kamervankrypto.dto.PortfolioDTO;
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
    PortfolioDTO getPortfolioByTrader(@RequestBody Trader trader) {
        return new PortfolioDTO(portfolioService.getByTrader(trader));
    }

    @GetMapping(value = "/assets/{ticker}")
    @ResponseBody
    Portfolio getWalletByTraderAndTicker(@RequestBody Trader trader, @PathVariable("ticker") String ticker) {
        return portfolioService.getWalletByTraderAndTicker(trader, ticker);
    }

    @PostMapping (value = "/update/{ticker}/{amountToAddOrSubtract}")
    @ResponseBody
    PortfolioDTO updateWallet(@RequestBody Trader trader, @PathVariable("ticker") String ticker, @PathVariable("amountToAddOrSubtract") double amount) {
        portfolioService.createOrUpdate(trader, ticker, amount);
        return new PortfolioDTO(portfolioService.getByTrader(trader));
    }

    @DeleteMapping ("/delete/{ticker}")
    public PortfolioDTO deleteWallet(@RequestBody Trader trader, @PathVariable(value = "ticker") String ticker) {
        portfolioService.delete(trader, ticker);
        return new PortfolioDTO(portfolioService.getByTrader(trader));
    }

}


