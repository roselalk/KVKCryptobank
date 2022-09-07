package com.example.kamervankrypto.repository.Portfolio;

import com.example.kamervankrypto.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcWalletDAO implements WalletDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcWalletDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Wallet> findAllWalletsByTraderId(int traderId) {
        String sql = "SELECT * FROM Wallet WHERE idTrader = ?;";
        return jdbcTemplate.query(sql, new WalletRowMapper(), traderId);
    }

    @Override
    public Wallet findWalletByTraderIdAndTicker(int traderId, String ticker) {
        String sql = "SELECT * FROM Wallet WHERE idTrader = ? AND ticker = ?;";
        List<Wallet> resultList = jdbcTemplate.query(sql, new WalletRowMapper(), traderId, ticker);
        return !resultList.isEmpty() ? resultList.get(0) : null;
    }

    @Override
    public void updateWallet(int traderId, String ticker, double amount) {
        String sql = "update Wallet set Amount = ? where idTrader = ? AND ticker = ?;";
        jdbcTemplate.update(sql, amount, traderId, ticker);

    }

    @Override
    public void createWallet(int traderId, String ticker, double amount) {
        String sql = "Insert into Wallet (idTrader, ticker, amount) values (?,?,?);";
        jdbcTemplate.update(sql, traderId, ticker, amount);
    }

    @Override
    public void deleteWallet(int traderId, String ticker) {
        String sql = "delete from wallet where idTrader = ? AND ticker = ?;";
        jdbcTemplate.update(sql, traderId, ticker);
    }

    private class WalletRowMapper implements RowMapper<Wallet> {
        @Override
        public Wallet mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Wallet(resultSet.getInt("idTrader"), resultSet.getString("Ticker"), resultSet.getDouble("Amount"));
        }
    }
}
