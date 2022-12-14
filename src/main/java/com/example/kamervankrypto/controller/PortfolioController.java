package com.example.kamervankrypto.controller;

import com.example.kamervankrypto.config.BadCredentialsException;
import com.example.kamervankrypto.dto.PortfolioDTO;
import com.example.kamervankrypto.dto.WalletDTO;
import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.model.Wallet;
import com.example.kamervankrypto.service.PortfolioService;
import com.example.kamervankrypto.service.TokenService;
import com.example.kamervankrypto.utils.ExceptionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(value = "/portfolio/")
public class PortfolioController {
    private PortfolioService portfolioService;
    private TokenService tokenService;

    @Autowired
    public PortfolioController(PortfolioService portfolioService, TokenService tokenService) {
        this.portfolioService = portfolioService;
        this.tokenService = tokenService;
    }

    @GetMapping(value = "/assets")
    @ResponseBody
    ResponseEntity<PortfolioDTO> getPortfolioByTrader(@RequestBody String token) {
        Trader trader = tokenService.checkTokenValidity(token);
        if (trader != null) {
            return ResponseEntity.ok(new PortfolioDTO(portfolioService.getByTrader(trader)));
        } else {
            throw new BadCredentialsException(ExceptionMessage.INVALID_TOKEN.toString());
        }
    }

    @GetMapping(value = "/assets/{ticker}")
    @ResponseBody
    ResponseEntity<WalletDTO> getWalletByTraderAndTicker(@RequestBody String token, @PathVariable("ticker") String ticker) {
        Trader trader = tokenService.checkTokenValidity(token);
        if (trader != null) {
            Optional<Wallet> wallet = Optional.ofNullable(portfolioService.getWalletByTraderAndTicker(trader, ticker));
            if (wallet.isPresent()) {
                return ResponseEntity.ok( new WalletDTO(wallet.get()) );
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionMessage.WALLET_NOT_FOUND.toString());
            }
        } else {
            throw new BadCredentialsException(ExceptionMessage.INVALID_TOKEN.toString());
        }
    }

    @PostMapping (value = "/update/{ticker}/{amountToAddOrSubtract}")
    @ResponseBody
    ResponseEntity<PortfolioDTO> updateWallet(@RequestBody String token, @PathVariable("ticker") String ticker, @PathVariable("amountToAddOrSubtract") double amount) {
        Trader trader = tokenService.checkTokenValidity(token);
        if (trader != null) {
            portfolioService.createOrUpdate(trader, ticker, amount);
            return ResponseEntity.ok(new PortfolioDTO(portfolioService.getByTrader(trader)));
        } else {
            throw new BadCredentialsException(ExceptionMessage.INVALID_TOKEN.toString());
        }
    }

    @DeleteMapping ("/delete/{ticker}")
    public ResponseEntity<PortfolioDTO> deleteWallet(@RequestBody String token, @PathVariable(value = "ticker") String ticker) {
        Trader trader = tokenService.checkTokenValidity(token);
        if (trader != null) {
            portfolioService.delete(trader, ticker);
            return ResponseEntity.ok(new PortfolioDTO(portfolioService.getByTrader(trader)));
        } else {
            throw new BadCredentialsException(ExceptionMessage.INVALID_TOKEN.toString());
        }
    }

}


