package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.Transaction;
import com.example.kamervankrypto.repository.TransactionDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private TransactionDAO transactionDAO;

    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }

    public List<Transaction> getAll() {
        return transactionDAO.findAll();
    }

    public Transaction getById(String idTransaction) {
        return transactionDAO.findById(idTransaction);
    }

}
