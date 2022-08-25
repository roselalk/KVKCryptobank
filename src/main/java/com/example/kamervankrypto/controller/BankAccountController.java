package com.example.kamervankrypto.controller;


import com.example.kamervankrypto.model.BankAccount;
import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping(value = "/BankAccount") // dit geld voor alles wat hieronder staat!
public class BankAccountController {

    private BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
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

        //return bankAccountService.getBankSaldo(id);
    }

    @PostMapping (value = "/create/{startSaldo}") // zelfde URL als de GET request, maar doet toch iets anders!
    BankAccount createBankAccount(@RequestBody Trader trader, @PathVariable("startSaldo") double startSaldo) {
        bankAccountService.createBankAccount(trader, startSaldo);
        return bankAccountService.getBankAccount(trader);
    }

    @PostMapping (value = "/update/{changeSaldo}")
    BankAccount changeBankSaldo(@RequestBody Trader trader, @PathVariable("changeSaldo") double changeSaldo) {
        BankAccount bankAccount = bankAccountService.getBankAccount(trader);
        bankAccountService.changeBankSaldo(bankAccount, changeSaldo);
        return bankAccountService.getBankAccount(bankAccount.getTrader());
    }

    @GetMapping
    void deleteBankAccount(@RequestBody Trader trader) {
        bankAccountService.deleteBankAccount(bankAccountService.getBankAccount(trader));
    }


}
