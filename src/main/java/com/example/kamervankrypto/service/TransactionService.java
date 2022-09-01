package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.model.Transaction;
import com.example.kamervankrypto.repository.TransactionDAO;
import com.example.kamervankrypto.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private TransactionDAO transactionDAO;
    private TransactionRepository transactionRepository;

    public TransactionService(TransactionDAO transactionDAO, TransactionRepository transactionRepository) {
        this.transactionDAO = transactionDAO;
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    public Transaction getById(int id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> getAllTransactionsForTrader(Trader trader) {
        return transactionRepository.getAllTransactionsForTrader(trader);
    }

    public List<Transaction> getTransactionBySeller(Trader seller) {
        return transactionRepository.getTransactionBySeller(seller);
    }

    public List<Transaction> getTransactionByBuyer(Trader buyer) {
        return transactionRepository.getTransactionByBuyer(buyer);
    }

    public void createTransaction(Transaction transaction) {
        transactionDAO.createTransaction(transaction);
    }

    public void updateTransaction(Transaction transaction) {
        transactionDAO.updateTransaction(transaction);
    }

    public void deleteTransaction(int idTransaction) {
        transactionDAO.deleteTransaction(idTransaction);
    }
}
