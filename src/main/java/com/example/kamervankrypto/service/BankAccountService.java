package com.example.kamervankrypto.service;


import com.example.kamervankrypto.model.BankAccount;
import com.example.kamervankrypto.repository.BankAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    private BankAccountDAO bankAccountDAO;

    @Autowired
    public BankAccountService(BankAccountDAO bankAccountDAO) {
        this.bankAccountDAO = bankAccountDAO;
    }

    public BankAccount getBankAccount(int id) {
        return bankAccountDAO.getBankAccount(id);
    }

    public int getBankSaldo(int id) {
        return bankAccountDAO.getBankSaldo(id);
    }
}
