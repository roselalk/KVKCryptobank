package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcAssetDAO implements AssetDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAssetDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Asset> getAll() {
        String sql = "SELECT * FROM Asset;";
        return jdbcTemplate.query(sql, new AssetRowMapper());
    }

    @Override
    public Asset getByTicker(String ticker) {
        String sql = "SELECT * FROM Asset WHERE Ticker = ?;";
        List<Asset> resultList = jdbcTemplate.query(sql, new AssetRowMapper(), ticker);
        return resultList.get(0);
    }

    @Override
    public Asset getByName(String name) {
        String sql = "SELECT * FROM Asset WHERE Name = ?;";
        List<Asset> resultList = jdbcTemplate.query(sql, new AssetRowMapper(), name);
        return resultList.get(0);
    }

    @Override
    public void save(Asset asset) {
        String sql = "INSERT INTO Asset (Ticker, Name) VALUES (?,?)";
        jdbcTemplate.update(sql, asset.getTicker(), asset.getName());
    }

    @Override
    public void delete(Asset asset) {

    }

    @Override
    public void update(Asset asset) {

    }

    private class AssetRowMapper implements RowMapper<Asset> {
        @Override
        public Asset mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Asset(resultSet.getString("Ticker"), resultSet.getString("Name"));
        }
    }
}
