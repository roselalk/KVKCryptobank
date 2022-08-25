package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.BankAccount;
import com.example.kamervankrypto.model.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BankAccountRepository {

    private BankAccountDAO bankAccountDAO;
    @Autowired
    public BankAccountRepository(BankAccountDAO bankAccountDAO) {
        this.bankAccountDAO = bankAccountDAO;
    }

    public BankAccount getBankAccount(Trader trader){
        BankAccount bankAccount = bankAccountDAO.getBankAccount(trader.getID());
        bankAccount.setTrader(trader);
        return bankAccount;
    }

}
