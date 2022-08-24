package com.example.kamervankrypto.repository;


import com.example.kamervankrypto.model.BankAccount;
import com.example.kamervankrypto.model.Trader;

public interface BankAccountDAO {

    BankAccount getBankAccount(Trader trader);

    default void create(BankAccount bankAccount){
    }

}