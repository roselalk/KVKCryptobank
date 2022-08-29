package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.model.Transaction;

import java.util.List;

public interface TransactionDAO {

    List<Transaction> findAll();

    Transaction findById(String idTransaction);

    void update(Transaction transaction);

    void save(Transaction transaction);

    Transaction getTransactionBySeller(Trader seller);

    Transaction getTransactionByBuyer(Trader buyer);
}
