package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.BankAccount;
import com.example.kamervankrypto.model.Trader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class JdbcTraderDAO implements TraderDAO {

    private JdbcTemplate jdbcTemplate;
    public JdbcTraderDAO (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //TODO
    private PreparedStatement insertTraderStatement(Trader trader, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into KamerVanKrypto.Trader (idTrader, Password," +
                "FirstName, Prefix, Name, BSN, Birthdate, Adress, Number, PostalCode, City, Email, Active) values " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, trader.getID());
        ps.setString(2, trader.getPassword());
        ps.setString(3, trader.getFirstName());
        ps.setString(4, trader.getPrefix());
        ps.setString(5, trader.getName());
        ps.setInt(6, trader.getBSN());
        ps.setString(7, String.valueOf(trader.getDateOfBirth()));
        ps.setString(8, trader.getStreet());
        ps.setString(9, trader.getHouseNumber());
        ps.setString(10, trader.getZipCode());
        ps.setString(11, trader.getCity());
        ps.setString(12, trader.getEmail());
        ps.setBoolean(13, trader.isActive());
        return ps;
    }

    @Override
    public List<Trader> findAll() {
        String sql = "SELECT * FROM Trader";
        return jdbcTemplate.query(sql, new TraderRowMapper());
    }

    @Override
    public void save(Trader trader) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> insertTraderStatement(trader, con), keyHolder);
        int newKey = keyHolder.getKey().intValue();
        trader.setID(newKey);
    }


    @Override
    public Trader findById(String id) {
        String sql = "SELECT * FROM Trader WHERE idTrader = ?";
        List<Trader> resultList = jdbcTemplate.query(sql, new TraderRowMapper(), id);
        if (resultList.size() == 0) {
            return null;
        } else {
            return resultList.get(0);
        }
    }


    @Override
    public Trader getTraderByName(String name) {
        //Wat als er 2 mensen met dezelfde naam zijn? Misschien beter zoeken op voor+achter?
        String sql = "SELECT * FROM Trader WHERE Name = ?";
        List<Trader> resultList = jdbcTemplate.query(sql, new TraderRowMapper(), name);
        if (resultList.size() == 0) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

    @Override
    public void update(Trader trader) {
        String sql = "UPDATE Trader SET Password = ?, FirstName = ?, Prefix = ?, Name = ?, BSN = ?, Birthdate = ?, Adress = ?," +
                "Number = ?, PostalCode = ?, City = ?, Email = ?, Active = ?";
        jdbcTemplate.update(sql, new TraderRowMapper());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Trader WHERE idTrader = ?";
        jdbcTemplate.update(sql, new TraderRowMapper());
    }

    private class TraderRowMapper implements RowMapper<Trader> {
        @Override
        public Trader mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            DateTimeFormatter dateFormat = Trader.DATEFORMAT;
                return new Trader(resultSet.getInt("idTrader"), resultSet.getString("Email"), resultSet.getString("Password"),
                        resultSet.getString("FirstName"), resultSet.getString("Prefix"), resultSet.getString("Name"),
                        resultSet.getInt("BSN"), LocalDate.parse(resultSet.getString("Birthdate"), dateFormat), resultSet.getString("Adress"),
                        resultSet.getString("Number"), resultSet.getString("PostalCode"), resultSet.getString("City"),
                        resultSet.getBoolean("Active"));
        }
    }


}
