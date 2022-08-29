package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.BankAccount;
import com.example.kamervankrypto.model.Trader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class JdbcBankAccountDAO implements BankAccountDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcBankAccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BankAccount getBankAccount(int idTrader) {
        String sql = "Select * from BankAccount Where idTrader = ?";
        List<BankAccount> resultList = jdbcTemplate.query(sql, new BankAccountRowMapper(), idTrader);
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(resultList.size()-1); // return last one, this has the correct (most recent) Saldo.
        }

    }

    @Override
    public void createBankAccount(BankAccount bankAccount) {
        String sql = "INSERT INTO  BankAccount (idTrader, Saldo, SaldoDateTime, IBAN) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, bankAccount.getTrader().getID(),
                bankAccount.getSaldo(),
                bankAccount.getSaldoDateTime(),
                bankAccount.getIban());
    }

    @Override
    public void deleteBankAccount(BankAccount bankAccount){
        String sql = "DELETE FROM BankAccount WHERE idTrader = ?;";
        jdbcTemplate.update(sql, bankAccount.getTrader().getID());
    }


    // ROWMAPPER

    private class BankAccountRowMapper implements RowMapper<BankAccount> {

        @Override
        public BankAccount mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

            return new BankAccount(resultSet.getDouble("Saldo"),
                        resultSet.getString("SaldoDateTime"),
                        resultSet.getString("IBAN"));
        }
    }

}
