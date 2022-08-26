package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Rate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcRateDAO implements RateDAO {

    @Override
    public List<Rate> getAllByTicker(String ticker) {
        return null;
    }

    @Override
    public Rate getCurrentByTicker(String ticker) {
        return null;
    }

    @Override
    public void create(Rate rate) {

    }

    private class RateRowMapper implements RowMapper<Rate> {
        @Override
        public Rate mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Rate(resultSet.getInt("idRate"), resultSet.getDouble("value"), resultSet.getLocalDateTime("date"));
        }
    }
}
