package com.example.kamervankrypto.repository;


import com.example.kamervankrypto.model.BankAccount;
import com.example.kamervankrypto.model.Trader;

public interface BankAccountDAO {

    BankAccount getBankAccount(int idTrader);

    default void createBankAccount(BankAccount bankAccount){
    }

    default void deleteBankAccount(BankAccount bankAccount){
    }

}