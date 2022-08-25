package com.example.kamervankrypto.repository;


import com.example.kamervankrypto.model.BankAccount;

public interface BankAccountDAO {

    BankAccount getBankAccount(int id);

    int getBankSaldo(int id);

    default void save(BankAccount bankAccount){

    }

}