package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcAssetDAO implements AssetDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAssetDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Asset> getAll() {
        String sql = "Select * From Asset";
        return jdbcTemplate.query(sql, new AssetRowMapper());
    }

    @Override
    public Asset findByTicker(String ticker) {
        String sql = "Select * From Asset Where Abbreviation = ?";
        return jdbcTemplate.queryForObject(sql, new AssetRowMapper(), ticker);
    }

    @Override
    public Asset findByName(String name) {
        String sql = "Select * From Asset Where Name = ?";
        return jdbcTemplate.queryForObject(sql, new AssetRowMapper(), name);
    }

    @Override
    public void save(Asset asset) {

    }

    @Override
    public void update(Asset asset) {

    }

    private class AssetRowMapper implements RowMapper<Asset> {
        @Override
        public Asset mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Asset(resultSet.getString("Abbreviation"), resultSet.getString("Name"));
        }
    }
}
