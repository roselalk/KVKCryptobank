package com.example.kamervankrypto.controller;


import com.example.kamervankrypto.model.BankAccount;
import com.example.kamervankrypto.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/BankAccount") // dit geld voor alles wat hieronder staat!
public class BankAccountController {

    private BankAccountService bankAccountService;

    @Autowired
    public BankAccountController(BankAccountService bankAccountService){
        this.bankAccountService = bankAccountService;
    }

    @GetMapping(value = "/{id}")
    BankAccount getBankAccount(@PathVariable("id") int id) {
        return bankAccountService.getBankAccount(id);
    }

    @GetMapping(value = "/saldo/{id}")
    int getBankSaldo(@PathVariable("id") int id) {
        return bankAccountService.getBankSaldo(id);
    }






}
