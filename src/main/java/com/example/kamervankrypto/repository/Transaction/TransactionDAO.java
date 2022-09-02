package com.example.kamervankrypto.repository.Transaction;

import com.example.kamervankrypto.model.Transaction;

import java.util.List;

public interface TransactionDAO {

    List<Transaction> findAll();

    Transaction findById(int idTransaction);

    void createTransaction(Transaction transaction);

    List<Transaction> getTransactionsByBuyerId(int idBuyer);

    List<Transaction> getTransactionsBySellerId(int idSeller);

    int getIdBuyerByIdTransaction(int idTransaction);

    int getIdSellerByIdTransaction(int idTransaction);

    String getTickerByTransactionId(int idTransaction);

    void updateTransaction(Transaction Transaction);

    void deleteTransaction(int idTransaction);


}
