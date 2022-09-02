package com.example.kamervankrypto.controller;


import com.example.kamervankrypto.model.BankAccount;
import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.service.BankAccountService;
import com.example.kamervankrypto.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(value = "/BankAccount") // dit geld voor alles wat hieronder staat!
public class BankAccountController {

    private BankAccountService bankAccountService;
    //TEST
    private TokenService tokenService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService, TokenService tokenService) {
        this.bankAccountService = bankAccountService;
        // TEST
        this.tokenService = tokenService;
    }

    @GetMapping(value = "/{token}")
//    BankAccount getBankAccount(@RequestBody Trader trader) {
    BankAccount getBankAccount(@PathVariable String token) {
        Trader trader = tokenService.checkTokenValidity(token);
        if (trader != null) {
            return bankAccountService.getBankAccount(trader);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not valid!");
        }
    }

    @GetMapping(value = "/saldo/{token}")
    double getBankSaldo(@PathVariable String token) {
        Trader trader = tokenService.checkTokenValidity(token);
        if(trader !=null){
            return bankAccountService.getBankAccount(trader).getSaldo();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not valid!");
        }

    }

//    @PutMapping(value = "/create/{startSaldo}")
    @PutMapping(value = "/create/{token}")  // werkt nog niet
    BankAccount createBankAccount(@PathVariable String token, @RequestParam("StartSaldo") double startSaldo) {
        Trader trader = tokenService.checkTokenValidity(token);
        if(trader !=null){
            bankAccountService.createBankAccount(trader, startSaldo);
            return bankAccountService.getBankAccount(trader);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not valid!");
        }


    }

    @PostMapping(value = "/update/{token}")  // werkt nog niet
    BankAccount changeBankSaldo(@PathVariable String token, @RequestParam("ChangeSaldo") double changeSaldo) {
        Trader trader = tokenService.checkTokenValidity(token);
        if(trader !=null){
            BankAccount bankAccount = bankAccountService.getBankAccount(trader);
            bankAccountService.changeBankSaldo(bankAccount, changeSaldo);
            return bankAccountService.getBankAccount(trader);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token not valid!");
        }
    }

    @PostMapping(value = "/delete")
    void deleteBankAccount(@RequestBody Trader trader) {
        bankAccountService.deleteBankAccount(bankAccountService.getBankAccount(trader));
    }

    // DIT IS ALLEEN VOOR TEST DOELEN!
    @GetMapping(value = "/test")
    String getToken(@RequestBody Trader trader) {

        return tokenService.createToken(trader);

    }

    @GetMapping(value = "/test/{token}")
    Trader getToken(@PathVariable String token) {

        return tokenService.checkTokenValidity(token);

    }

}
