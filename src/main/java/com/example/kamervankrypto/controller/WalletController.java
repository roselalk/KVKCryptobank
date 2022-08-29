package com.example.kamervankrypto.controller;
import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.model.Wallet;
import com.example.kamervankrypto.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    @ResponseBody
    List<Wallet> getPortfolioByTrader(@RequestBody Trader trader) {return walletService.getAllByTrader(trader);}
    //portfolio represents all the wallets for a specific user, not all the wallets from the table wallet in sql:

    @GetMapping(value = "/{asset}")
    @ResponseBody
    Wallet getWalletByAssetTicker(@RequestBody Trader trader, @PathVariable("asset") String ticker) {
        //needs the asset ticker to find a specific wallet for the logged-in user:
        //localhost:8080/portfolio/btc
        Optional<Wallet> wallet =
                walletService.getByTraderIdAndAssetTicker(trader, ticker);
        if (wallet.isPresent()) {
            return wallet.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such wallet found!");
        }
    }

    @PutMapping(value = "/{asset}/{amount}")
    @ResponseBody  List<Wallet> createWallet(@RequestBody Trader trader, @PathVariable("asset") String ticker, @PathVariable("amount") double amount) {
        walletService.save(trader, ticker, amount);
        return walletService.getAllByTrader(trader);
    }

    @PostMapping (value = "/update/{idWallet}/{amount}")
    @ResponseBody List<Wallet> updateWallet(@RequestBody Trader trader, @PathVariable("idWallet") int idWallet, @PathVariable("amount") double amount) {
        walletService.update(idWallet, amount);
        return walletService.getAllByTrader(trader);
    }

    @DeleteMapping ("/delete/{asset}")
    public ResponseEntity<Wallet> deleteWallet(@RequestBody Trader trader, @PathVariable(value = "asset") String ticker) {
        Wallet wallet = walletService.delete(trader, ticker);
        return ResponseEntity.ok(wallet);
    }

}
