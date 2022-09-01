package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository

public class TransactionRepository {
    private final JdbcTemplate jdbcTemplate;
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

    //  Returns a list of known transactions in the DB, descending from the newest idTransaction.
    //  TODO: Should not be used by basic user, this is an admin-only method.
    public List<Transaction> findAll() {
        String sql = "SELECT * FROM Transaction ORDER BY idTransaction DESC;";
        return jdbcTemplate.query(sql, new TransactionRepositoryRowMapper());
    }

    //  Returns a single transaction for a given transaction ID.
    public Transaction findById(int idTransaction) {
        String sql = "SELECT * FROM transaction WHERE idTransaction = ? ;";
        List<Transaction> returnlist = jdbcTemplate.query(sql, new TransactionRepositoryRowMapper(), idTransaction);
        if (returnlist.size() == 0) {
            return null;
        } else {
            return returnlist.get(0);
        }
    }

    //  Returns a single list containing all transactions relating to a given trader.
    public List<Transaction> getAllTransactionsForTrader(Trader trader) {
        List<Transaction> t_all = new ArrayList<>();
        t_all.addAll(getTransactionByBuyer(trader));
        t_all.addAll(getTransactionBySeller(trader));
        return t_all;

    }

    //  Returns a list of transactions where the given trader is buyer.
    public List<Transaction> getTransactionByBuyer(Trader buyer) {
        String sql = "SELECT * FROM transaction WHERE idBuyer = ?;";
        return jdbcTemplate.query(sql, new TransactionRepositoryRowMapper(), buyer.getID());
    }

    //  Returns a list of transactions where the given trader is seller.
    public List<Transaction> getTransactionBySeller(Trader seller) {
        String sql = "SELECT * FROM transaction WHERE idSeller = ?;";
        return jdbcTemplate.query(sql, new TransactionRepositoryRowMapper(), seller.getID());
    }

    //  Inserts Traders(buyer/seller) and Asset into Transaction based on DB-references, returning a complete
    //  Transaction object.
    private class TransactionRepositoryRowMapper implements RowMapper<Transaction> {
        public Transaction mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            Transaction t = transactionDAO.findById(resultSet.getInt("idTransaction"));
            t.setBuyer(traderDAO.findById(resultSet.getInt("idBuyer")));
            t.setSeller(traderDAO.findById(resultSet.getInt("idSeller")));
            t.setAsset(assetDAO.getByTicker(resultSet.getString("Ticker")));
            return t;
        }
    }
}
