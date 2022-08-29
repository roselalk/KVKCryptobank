package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcTransactionDAO implements TransactionDAO {
    @Override
    public List<Transaction> findAll() {
        return null;
    }

    @Override
    public Transaction findById(String idTransaction) {
        return null;
    }

    @Override
    public void update(Transaction transaction) {
    }

    @Override
    public void save(Transaction transaction) {
    }

    @Override
    public Transaction getTransactionBySeller(Trader seller) {
        return null;
    }

    @Override
    public Transaction getTransactionByBuyer(Trader buyer) {
        return null;
    }
}
