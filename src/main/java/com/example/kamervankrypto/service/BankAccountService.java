package com.example.kamervankrypto.service;


import com.example.kamervankrypto.model.BankAccount;
import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.repository.BankAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Service
public class BankAccountService {

    private BankAccountDAO bankAccountDAO;

    @Autowired
    public BankAccountService(BankAccountDAO bankAccountDAO) {
        this.bankAccountDAO = bankAccountDAO;
    }

    public BankAccount getBankAccount(Trader trader) {
        return bankAccountDAO.getBankAccount(trader);
    }


    public void create(Trader trader, double startSaldo) {
        BankAccount bankAccount = new BankAccount(trader, startSaldo);
        bankAccountDAO.create(bankAccount);
    }

    public void change(BankAccount bankAccount, double changeSaldo) {
        bankAccount.setSaldoDateTime(DateTime.toString());
        bankAccount.setSaldo(bankAccount.getSaldo() + changeSaldo);
        bankAccountDAO.create(bankAccount); // Every change is an addition to the MySQL table with a DateTime change, therefore create!
    }
}
