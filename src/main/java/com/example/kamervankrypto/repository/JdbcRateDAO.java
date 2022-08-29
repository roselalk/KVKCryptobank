package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> insertRateStatement(rate, connection), keyHolder);
        int newKey = keyHolder.getKey().intValue();
        rate.setRateId(newKey);
    }

    private PreparedStatement insertRateStatement(Rate rate, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO Rate (RateDateTime, Ticker, Rate) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, rate.getDate());
        ps.setString(2, rate.getPair().getTicker()); //waar komt deze vandaan? //TODO
        ps.setDouble(3, rate.getValue());
        return ps;
    }

    private class RateRowMapper implements RowMapper<Rate> {
        @Override
        public Rate mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Rate(resultSet.getInt("RateId"), resultSet.getDouble("Rate"), resultSet.getString("RateDateTime"));
        }
    }
}
