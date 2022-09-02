package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.model.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

@Repository
public class JdbcPortfolioDAO implements PortfolioDAO{

    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcPortfolioDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Portfolio findByTraderId(int traderId) {
        String sql = "SELECT w.amount, a.ticker, a.name " +
                "FROM wallet w left join asset a on w.ticker = a.ticker  where w.idtrader = ?;";
        return jdbcTemplate.query(sql, new PortfolioWithAssetsResultSetExtractor(), traderId);
    }

    @Override
    public Portfolio findWalletByTraderIdAndTicker(int traderId, String ticker) {
        String sql = "SELECT w.amount, a.ticker, a.name " +
                "FROM wallet w left join asset a on w.ticker = a.ticker  where w.idtrader = ? and w.ticker = ?;";
        return jdbcTemplate.query(sql, new PortfolioWithAssetsResultSetExtractor(), traderId, ticker);
    }

    @Override
    public void update(int traderId, String ticker, double amount) {
        String sql = "update Wallet set Amount = ? where idTrader = ? AND ticker = ?;";
        jdbcTemplate.update(sql, amount, traderId, ticker);
    }

    @Override
    public void create(int traderId, String ticker, double amount) {
        String sql = "Insert into Wallet (idTrader, ticker, amount) values (?,?,?);";
        jdbcTemplate.update(sql, traderId, ticker, amount);
    }

    @Override
    public void delete(int traderId, String ticker) {
        String sql = "delete from wallet where idTrader = ? AND ticker = ?;";
        jdbcTemplate.update(sql, traderId, ticker);
    }

    private class PortfolioWithAssetsResultSetExtractor implements ResultSetExtractor<Portfolio> {
        @Override
        public Portfolio extractData(ResultSet rs) throws SQLException, DataAccessException {
            Map<Asset, Double> assets = new TreeMap<>();
            while (rs.next()) {
                Asset asset = new Asset(rs.getString("ticker"), rs.getString("name"));
                assets.put(asset, rs.getDouble("amount"));
            }
            return new Portfolio(assets);
        }
    }
}
