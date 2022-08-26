package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.model.Transaction;
import org.springframework.stereotype.Repository;

import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcTransactionDAO implements TransactionDAO {
    @Override
    public List<Transaction> findAll() {
        return null;
    }

    @Override
    public Transaction findById(String idTransaction) {
        return null;
    }

    //  Het updaten van een transactie kan niet zonder meer, hiervoor zou er een herberekening plaatst moeten vinden van o.a.
    //  de saldi hetgeen een cummulatief effect heeft doordat alle latere transacties mogelijkerwijs niet tot uitvoering hadden
    //  kunnen komen.

    @Override
    public void update(Transaction transaction) {
    }

    @Override
    public void save(Transaction transaction) {
    }

    @Override
    public List<Transaction> getTransactionBySeller(Trader seller) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionByBuyer(Trader buyer) {
        return null;
    }

//    private class TransactionRowMapper implements RowMapper<Transaction> {
//        @Override
//        public Transaction mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
//       return new Transaction(resultSet.getInt())
//
//
//
//        }
//    }
}
