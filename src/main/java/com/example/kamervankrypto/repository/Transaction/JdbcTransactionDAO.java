package com.example.kamervankrypto.repository.Transaction;

import com.example.kamervankrypto.model.Transaction;
import com.example.kamervankrypto.repository.Transaction.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Objects;

@Repository
public class JdbcTransactionDAO implements TransactionDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTransactionDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //  Returns a list of known transactions in the DB, descending from the newest idTransaction.

    public List<Transaction> findAll() {
        String sql = "SELECT * FROM Transaction ORDER BY idTransaction DESC;";
        return jdbcTemplate.query(sql, new TransactionRowMapper());
    }

    //  Finds single transaction for give Transaction ID.

    public Transaction findById(int idTransaction) {
        String sql = "SELECT * FROM Transaction WHERE idTransaction = ?";
        List<Transaction> resultList = jdbcTemplate.query(sql, new TransactionRowMapper(), idTransaction);
        if (resultList.size() == 0) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

    //  Creates a new DB-entry for given Transaction
    public void createTransaction(Transaction transaction) {
        KeyHolder keyholder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> transactionPreparedStatement(transaction, connection), keyholder);
        int newKey = Objects.requireNonNull(keyholder.getKey()).intValue();
        transaction.setIdTransaction(newKey);
    }

    //  Helper method to facilitate auto-increment of idTransaction in createTransaction()
    private PreparedStatement transactionPreparedStatement(Transaction transaction, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO Transaction (Amount1, TransactionFee, TransactionDateTime, idBuyer,idSeller,Ticker) VALUES (?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
        ps.setDouble(1, transaction.getAmount1());
        ps.setDouble(2, transaction.getTransactionFee());
        ps.setString(3, transaction.getTransactionDateTime());
        ps.setInt(4, transaction.getBuyer().getID());
        ps.setInt(5, transaction.getSeller().getID());
        ps.setString(6, transaction.getAsset().getTicker());
        return ps;
    }

    //  TODO Discuss in team / With PO:
    //  Updating  Transactions should not be possible: Updating a single transaction implies the recalculation
    //  of all following transactions, because updating a transaction may create a state where not enough saldo is
    //  available to complete any given following transaction.
    //  Transactions should be considered final, updating only being possible through corrective transactions.
    //  Method is only here for demonstration of CRUD-Functionality.
    public void updateTransaction(Transaction transaction) {
        jdbcTemplate.update("UPDATE transaction SET Amount1 = ?, TransactionFee = ?, TransactionDateTime = ?, idBuyer = ?, idSeller = ?, Ticker = ? WHERE idTransaction = ?",
                transaction.getAmount1(),
                transaction.getTransactionFee(),
                transaction.getTransactionDateTime(),
                transaction.getBuyer().getID(),
                transaction.getSeller().getID(),
                transaction.getAsset().getTicker(),
                transaction.getIdTransaction());
    }

    //  Delete entry from DB for given idTransaction
    public void deleteTransaction(int idTransaction) {
        String sql = "DELETE FROM Transaction WHERE idTransaction = ?";
        jdbcTemplate.update(sql, idTransaction);
        System.out.println("Deletion of Transaction with id=" + idTransaction + " was successful.");
    }

    //  Returns list of transactions for given idBuyer
    public List<Transaction> getTransactionsByBuyerId(int idBuyer) {
        String sql = "SELECT * FROM Transaction WHERE idBuyer = ? ORDER BY idTransaction DESC;";
        return jdbcTemplate.query(sql, new TransactionRowMapper(), idBuyer);
    }

    //  Returns list of transactions for given idSeller
    public List<Transaction> getTransactionsBySellerId(int idSeller) {
        String sql = "SELECT * FROM Transaction WHERE idSeller = ? ORDER BY idTransaction DESC;";
        return jdbcTemplate.query(sql, new TransactionRowMapper(), idSeller);
    }

    //  Returns idBuyer for given idTransaction
    public int getIdBuyerByIdTransaction(int idTransaction) {
        String sql = "SELECT idBuyer FROM Transaction WHERE idTransaction=?";
        return jdbcTemplate.queryForObject(sql, Integer.class, idTransaction);
    }    //  Returns idSeller for given idTransaction

    //  Returns idSeller for given idTransaction
    public int getIdSellerByIdTransaction(int idTransaction) {
        String sql = "SELECT idSeller FROM Transaction WHERE idTransaction=? ORDER BY idTransaction DESC;";
        return jdbcTemplate.queryForObject(sql, Integer.class, idTransaction);
    }

    //  Returns Ticker for given idTransaction
    public String getTickerByTransactionId(int idTransaction) {
        String sql = "SELECT Ticker FROM Transaction WHERE idTransaction=?";
        return jdbcTemplate.queryForObject(sql, String.class, idTransaction);
    }
    //  Helper method returns basic Transaction-Object, without Buyer, Seller and Asset.
    private class TransactionRowMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Transaction(
                    resultSet.getInt("idTransaction"),
                    resultSet.getDouble("Amount1"),
                    resultSet.getDouble("TransactionFee"),
                    resultSet.getString("TransactionDateTime"));
        }
    }
}
