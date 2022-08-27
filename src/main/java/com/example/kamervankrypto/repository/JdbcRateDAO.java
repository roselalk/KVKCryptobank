package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Asset;
import com.example.kamervankrypto.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        //TODO implement
        String sql = "SELECT * FROM Rate WHERE Ticker = ?;";
        List<Rate> resultList = jdbcTemplate.query(sql, new RateRowMapper(), ticker);
    return resultList;
    }

    @Override
    public Rate getCurrentByTicker(String ticker) {
        //TODO implement
        String sql = "SELECT * FROM Rate WHERE Ticker = ? ORDER BY RateDateTime DESC LIMIT 1;";
        List<Rate> resultList = jdbcTemplate.query(sql, new RateRowMapper(), ticker);
        return resultList.get(0);

    }

    @Override
    public void create(Rate rate) {

    }

    private class RateRowMapper implements RowMapper<Rate> {
        @Override
        public Rate mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Rate(resultSet.getInt("idRate"), resultSet.getDouble("Rate"), resultSet.getString("RateDateTime"));
        }
    }
}
