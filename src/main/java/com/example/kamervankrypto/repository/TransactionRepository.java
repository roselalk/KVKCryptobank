package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TransactionRepository {
    private final JdbcTemplate   jdbcTemplate;
    private final TransactionDAO transactionDAO;
    private final TraderDAO traderDAO;
    private final AssetDAO assetDAO;

    @Autowired
    public TransactionRepository(JdbcTemplate jdbcTemplate, TransactionDAO transactionDAO, TraderDAO traderDAO, AssetDAO assetDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.transactionDAO = transactionDAO;
        this.traderDAO = traderDAO;
        this.assetDAO = assetDAO;
    }

    public List<Transaction> findAll() {
        String sql = "SELECT * FROM Transaction;";
        return jdbcTemplate.query(sql, new TransactionRepository.TransactionRepositoryRowMapper());
    }

    public List<Transaction> getTransactionBySeller(Trader seller) {
        String sql = "SELECT * FROM Transaction WHERE idSeller = ?;";
        return jdbcTemplate.query(sql, new TransactionRepository.TransactionRepositoryRowMapper(), seller.getID());
    }

    public List<Transaction> getTransactionByBuyer(Trader buyer) {
        String sql = "SELECT * FROM Transaction WHERE idBuyer = ?;";
        return jdbcTemplate.query(sql, new TransactionRepository.TransactionRepositoryRowMapper(), buyer.getID());
    }
    // Inserts Traders(buyer/seller) and Asset into Transaction based on DB-references.
    private class TransactionRepositoryRowMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            Transaction t = transactionDAO.findById(resultSet.getInt("idTransaction"));
            t.setBuyer(traderDAO.findById(String.valueOf(resultSet.getInt("idBuyer"))));
            t.setSeller(traderDAO.findById(String.valueOf(resultSet.getInt("idSeller"))));
            t.setAsset(assetDAO.getByTicker(resultSet.getString("Ticker")));
            return t;
        }
    }
}