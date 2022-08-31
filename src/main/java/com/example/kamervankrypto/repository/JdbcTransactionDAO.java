package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class JdbcTransactionDAO implements TransactionDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTransactionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //  Finds all transactions in Database.
    @Override
    public List<Transaction> findAll() {
        String sql = "SELECT * FROM Transaction;";
        return jdbcTemplate.query(sql, new TransactionRowMapper());
    }

    //  Finds single transaction for give Transaction ID.
    @Override
    public Transaction findById(int idTransaction) {
        String sql = "SELECT * FROM Transaction WHERE idTransaction = ?";
        List<Transaction> resultList = jdbcTemplate.query(sql, new TransactionRowMapper(), idTransaction);
        if (resultList.size() == 0) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

    public void update(Transaction transaction) {
        //  TODO Discuss in team / With PO:
        //  Updating  Transactions should not be possible: Updating a single transaction implies the recalculation
        //  of all following transaction, because updating a transaction may create a state where not enough saldo is
        //  available to complete any given following transaction.
        //  Transactions should be considered final, updating only being possible through corrective transactions.
    }

    @Override
    //  TODO Toevoegen met auto-increment.
    public void createTransaction(Transaction transaction) {
        String sql = "insert into Transaction " +
                "(idTransaction, Amount1, TransactionFee, TransactionDateTime, idBuyer,idSeller,Ticker )" +
                " values (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,
                transaction.getIdTransaction(),
                transaction.getAmount1(),
                transaction.getTransactionFee(),
                transaction.getTransactionDateTime(),
                transaction.getBuyer().getID(),
                transaction.getSeller().getID(),
                transaction.getAsset().getTicker());
    }

    //  TODO Possibly redundant, may be useful for use in other repositories, otherwise remove later.
    @Override
    public List<Transaction> getTransactionByBuyerId(int idBuyer) {
        String sql = "SELECT * FROM Transaction WHERE idBuyer = ?";
        return jdbcTemplate.query(sql, new TransactionRowMapper(), idBuyer);
    }

    //  TODO Possibly redundant, may be useful for use in other repositories, otherwise remove later.
    @Override
    public List<Transaction> getTransactionBySellerId(int idSeller) {
        String sql = "SELECT * FROM Transaction WHERE idSeller = ?";
        return jdbcTemplate.query(sql, new TransactionRowMapper(), idSeller);
    }

    @Override
    public void deleteTransaction(int idTransaction) {
        String sql = "DELETE FROM Transaction WHERE idTransaction = ?";
        jdbcTemplate.update(sql, idTransaction);
        System.out.println("Deletion of Transaction with id=" + idTransaction + " was successful.");
    }

    private class TransactionRowMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Transaction(
                    resultSet.getInt("idTransaction"),
                    resultSet.getDouble("Amount1"),
                    resultSet.getDouble("TransactionFee"),
                    resultSet.getString("TransactiondateTime"));
        }
    }
}
