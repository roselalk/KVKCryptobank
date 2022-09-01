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

    @GetMapping
    BankAccount getBankAccount(@RequestBody Trader trader) {
        Optional<BankAccount> bankAccount = Optional.ofNullable(bankAccountService.getBankAccount(trader));
        if (bankAccount.isPresent()) {
            return bankAccount.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No bank account found for this trader!");
        }
        //return bankAccountService.getBankAccount(id);
    }

    @GetMapping(value = "/saldo")
    double getBankSaldo(@RequestBody Trader trader)  {

        return getBankAccount(trader).getSaldo();
    }

    @PutMapping (value = "/create/{startSaldo}")
    BankAccount createBankAccount(@RequestBody Trader trader, @PathVariable("startSaldo") double startSaldo) {
        bankAccountService.createBankAccount(trader, startSaldo);
        return bankAccountService.getBankAccount(trader);
    }

    @PostMapping (value = "/update/{changeSaldo}")
    BankAccount changeBankSaldo(@RequestBody Trader trader, @PathVariable("changeSaldo") double changeSaldo) {
        BankAccount bankAccount = bankAccountService.getBankAccount(trader);
        bankAccountService.changeBankSaldo(bankAccount, changeSaldo);
        return bankAccountService.getBankAccount(trader);
    }

    @PostMapping(value = "/delete")
    void deleteBankAccount(@RequestBody Trader trader) {
        bankAccountService.deleteBankAccount(bankAccountService.getBankAccount(trader));
    }

    // DIT IS ALLEEN VOOR TEST DOELEN!
    @GetMapping(value = "/test")
    String getToken(@RequestBody Trader trader )  {

        return tokenService.createToken(trader);

    }

    @GetMapping(value = "/test/{token}")
    Trader getToken(@PathVariable String token )  {

        return tokenService.checkTokenValidity(token);

    }

}
