package com.example.kamervankrypto.service;


import com.example.kamervankrypto.model.BankAccount;
import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.repository.BankAccountDAO;
import com.example.kamervankrypto.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    private BankAccountDAO bankAccountDAO;
    private BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountService(BankAccountDAO bankAccountDAO, BankAccountRepository bankAccountRepository) {
        this.bankAccountDAO = bankAccountDAO;
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccount getBankAccount(Trader trader) {
        return bankAccountRepository.getBankAccount(trader);
    }


    public void createBankAccount(Trader trader, double startSaldo) {
        BankAccount bankAccount = new BankAccount(trader, startSaldo);
        bankAccountDAO.createBankAccount(bankAccount);
    }

    public void changeBankSaldo(BankAccount bankAccount, double changeSaldo) {
        bankAccount.setSaldoDateTime(BankAccount.getCurrentDateTime().toString());
        bankAccount.setSaldo(bankAccount.getSaldo() + changeSaldo);
        bankAccountDAO.createBankAccount(bankAccount); // Every change is an addition to the MySQL table with a DateTime change, therefore create!
    }

    public void deleteBankAccount(BankAccount bankAccount) {
        double Saldo = bankAccount.getSaldo();
        // TODO: Salo naar Bank terug schrijven
        bankAccountDAO.deleteBankAccount(bankAccount);
    }
}
