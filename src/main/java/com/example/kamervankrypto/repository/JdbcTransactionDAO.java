package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcTransactionDAO implements TransactionDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTransactionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transaction> findAll() {
        String sql = "SELECT * FROM Transaction;";
        return jdbcTemplate.query(sql, new TransactionRowMapper());
    }

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

    @Override
    public void update(Transaction transaction) {
        //  TODO Discuss in team:
        //  Updating  Transactions should not be possible: Updating a single transaction implies the recalculation
        //  of all following transaction, because updating a transaction may create a state where not enough saldo is
        //  available to complete any given following transaction.
        //  Transactions should be considered final, updating only being possible through corrective transactions.
    }

    @Override
        //  TODO Toevoegen met auto-increment.
    public void save(Transaction transaction) {
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

    @Override
    public List<Transaction> getTransactionByBuyerId(int idBuyer) {
        String sql = "SELECT * FROM Transaction WHERE idBuyer = ?";
        List<Transaction> resultList = jdbcTemplate.query(sql, new TransactionRowMapper(), idBuyer);
        return resultList;
    }

    @Override
    public List<Transaction> getTransactionBySellerId(int idSeller) {
        String sql = "SELECT * FROM Transaction WHERE idSeller = ?";
        List<Transaction> resultList = jdbcTemplate.query(sql, new TransactionRowMapper(), idSeller);
        return resultList;
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
