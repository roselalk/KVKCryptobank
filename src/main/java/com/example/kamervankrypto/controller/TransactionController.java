package com.example.kamervankrypto.controller;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.model.Transaction;
import com.example.kamervankrypto.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    //  TODO Likely to be redundant,  method is admin-only, may be helpful for overviews of all transactions, discuss in team / With PO.
    @GetMapping
    @ResponseBody
    List<Transaction> getTransactions() {
        return transactionService.getAll();
    }

    //  TODO Likely to be redundant, not useful from user perspective.
    @GetMapping(value = "/{idTransaction}")
    @ResponseBody
    Transaction getTraderById(@PathVariable("idTransaction") int id) {
        Optional<Transaction> transaction = Optional.ofNullable(transactionService.getById(id));
        if (transaction.isPresent()) {
            return transaction.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No transaction found with this ID.");
        }
    }

    @GetMapping(value = "/allTransactionsForTrader")
    @ResponseBody
    List<Transaction> getAllTransactionsForTrader(@RequestBody Trader trader) {
        Optional<List<Transaction>> transactions = Optional.ofNullable(transactionService.getAllTransactionsForTrader(trader));
        if (transactions.isPresent()) {
            return transactions.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No transactions found for this trader.");
        }
    }

    @GetMapping(value = "/allTransactionsAsSeller")
    @ResponseBody
    List<Transaction> getTransactionBySeller(@RequestBody Trader seller) {
        Optional<List<Transaction>> transactions = Optional.ofNullable(transactionService.getTransactionBySeller(seller));
        if (transactions.isPresent()) {
            return transactions.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No transactions found where this trader is seller.");
        }
    }

    @GetMapping(value = "/allTransactionsAsBuyer")
    @ResponseBody
    List<Transaction> getTransactionByBuyer(@RequestBody Trader buyer) {
        Optional<List<Transaction>> transactions = Optional.ofNullable(transactionService.getTransactionByBuyer(buyer));
        if (transactions.isPresent()) {
            return transactions.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No transactions found where this trader is buyer.");
        }
    }

    @PutMapping
    List<Transaction> createTransaction(@RequestBody Transaction transaction) {
        transactionService.createTransaction(transaction);
        //  TODO: Unclear if this should give a return, returns getAll() for testing purposes.
        return transactionService.getAll();
    }

    @DeleteMapping("/{idTransaction}")
    ResponseEntity<List<Transaction>> deleteTransaction(@PathVariable(value = "idTransaction") int id) {
        transactionService.deleteTransaction(id);
        //  TODO: Returns getAll(). Both getAll() and deleteTransaction should be considered Admin-only!
        return ResponseEntity.ok(transactionService.getAll());
    }
}
