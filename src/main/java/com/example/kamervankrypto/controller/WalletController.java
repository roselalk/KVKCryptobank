package com.example.kamervankrypto.controller;
import com.example.kamervankrypto.model.Wallet;
import com.example.kamervankrypto.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/portfolio")
public class WalletController {
    private WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    //wallets represents all the wallets FOR THE LOGGED-IN USER, not all the wallets from the table wallet in sql:
    @GetMapping
    @ResponseBody
    List<Wallet> getWallets() {return walletService.getAllByTrader();}

    //needs the asset ticker to find a specific wallet for the logged-in user: localhost:8080/portfolio/btc
    @GetMapping(value = "/{asset}")
    @ResponseBody
    Wallet getWalletByAssetTicker(@PathVariable("asset") String ticker) {
        Optional<Wallet> wallet =
                walletService.getByTraderIdAndAssetTicker(ticker);
        if (wallet.isPresent()) {
            return wallet.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such wallet found!");
        }
    }

    @PostMapping
    @ResponseBody  List<Wallet> createWallet(@RequestBody Wallet wallet) {
        walletService.save(wallet);
        return walletService.getAllByTrader();
    }

    @PostMapping
    @ResponseBody  List<Wallet> updateWallet(@RequestBody Wallet wallet) {
        walletService.update(wallet);
        return walletService.getAllByTrader();
    }


}