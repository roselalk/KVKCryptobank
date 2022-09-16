package com.example.kamervankrypto.repository.Rate;

import com.example.kamervankrypto.model.Rate;
import com.example.kamervankrypto.repository.Rate.RateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class JdbcRateDAO implements RateDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRateDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Rate> getAllByTicker(String ticker) {
        String sql = "SELECT * FROM Rate WHERE Ticker = ?;";
        return jdbcTemplate.query(sql, new RateRowMapper(), ticker);
    }

    @Override
    public Rate getCurrentByTicker(String ticker) {
        String sql = "SELECT * FROM Rate WHERE Ticker = ? ORDER BY RateDateTime DESC LIMIT 1;";
        List<Rate> resultList = jdbcTemplate.query(sql, new RateRowMapper(), ticker);
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void store(Rate rate) {

    }

    private class RateRowMapper implements RowMapper<Rate> {
        @Override
        public Rate mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Rate(resultSet.getDouble("Rate"), resultSet.getString("RateDateTime"));
        }
    }
}
