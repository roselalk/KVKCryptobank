package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.model.Transaction;

import java.util.List;

public interface TransactionDAO {

    List<Transaction> findAll();

    Transaction findById(int idTransaction);

    void createTransaction(Transaction transaction);

    List<Transaction> getTransactionByBuyerId(int idBuyer);

    List<Transaction> getTransactionBySellerId(int idSeller);

    void updateTransaction(Transaction Transaction);

    void deleteTransaction(int idTransaction);


}
