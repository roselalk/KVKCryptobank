package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcBankAccountDAO implements BankAccountDAO{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcBankAccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BankAccount getBankAccount(int id) {
        return null;
    }

    @Override
    public int getBankSaldo(int id) {
        return 0;
    }

    @Override
    public void save(BankAccount bankAccount) {
        BankAccountDAO.super.save(bankAccount);
    }
}
