package com.example.kamervankrypto.controller;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.model.Transaction;
import com.example.kamervankrypto.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {this.transactionService = transactionService;}

    @GetMapping
    @ResponseBody
    List<Transaction> getTransactions() {return  transactionService.getAll();}

    @GetMapping(value = "/{idTransaction}")
    @ResponseBody
    Transaction getTraderById(@PathVariable("idTransaction") String id) {
        Optional<Transaction> transaction = Optional.ofNullable(transactionService.getById(id));
        if (transaction.isPresent()) {
            return transaction.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No transaction found with this ID!");
        }
    }
  }
