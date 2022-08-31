package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcWalletDAO implements WalletDAO {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcWalletDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Wallet> findAllByTraderId(int id) {
        String sql = "SELECT w.idWallet, w.amount, a.ticker, a.name " +
                "FROM wallet w left join asset a on w.ticker = a.ticker  where w.idtrader = ?;";
        return jdbcTemplate.query(sql, new WalletsWithAssetsResultSetExtractor(), id);
    }

    public Optional<Wallet> findByTraderIdAndAsset(int id, String ticker) {
        String sql = "Select * from Wallet Where idTrader = ? AND Ticker = ?;";
        List<Wallet> resultList = jdbcTemplate.query(sql, new WalletRowMapper(), id, ticker);
        if (resultList.size() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(resultList.get(0));
        }
    }

    @Override
    public void save(Wallet wallet) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> buildInsertWalletStatement(wallet, connection), keyHolder);
        wallet.setIdWallet(keyHolder.getKey().intValue());
    }

    private PreparedStatement buildInsertWalletStatement(Wallet wallet, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "Insert into Wallet (idTrader, ticker, amount) values (?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, wallet.getTrader().getID());
        ps.setString(2, wallet.getAsset().getTicker());
        ps.setDouble(3, wallet.getAmount());
        return ps;
    }

    @Override
    public void update(int idWallet, double amount) {
        String sql = "update Wallet set Amount = ? where idWallet = ?;";
        jdbcTemplate.update(sql, amount, idWallet);
    }

    //todo: we might not need this method, but it is here for CRUD functionality
    @Override

    public void delete(int idWallet) {
        String sql = "delete from wallet where idWallet = ?;";
        jdbcTemplate.update(sql, idWallet);
    }

    private class WalletRowMapper implements RowMapper<Wallet> {
        @Override
        public Wallet mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Wallet(resultSet.getInt("IdWallet"), resultSet.getDouble("Amount"));
        }
    }

    private class WalletsWithAssetsResultSetExtractor implements ResultSetExtractor<List<Wallet>> {
        @Override
        public List<Wallet> extractData(ResultSet rs) throws SQLException, DataAccessException {
            List<Wallet> portfolio = new ArrayList<>();
            while (rs.next()) {
                Wallet wallet = new Wallet(rs.getInt("idWallet"), rs.getDouble("amount"));
                Asset asset = new Asset(rs.getString("ticker"), rs.getString("name"));
                wallet.setAsset(asset);
                portfolio.add(wallet);
            }
            return portfolio;
        }
    }
}



