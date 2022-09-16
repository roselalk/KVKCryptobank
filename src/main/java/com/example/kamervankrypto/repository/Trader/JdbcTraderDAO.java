package com.example.kamervankrypto.repository.Trader;

import com.example.kamervankrypto.model.Trader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class JdbcTraderDAO implements TraderDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcTraderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private PreparedStatement insertTraderStatement(Trader trader, Connection connection) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("insert into KamerVanKrypto.Trader (idTrader, Password," +
                "FirstName, Prefix, Name, BSN, Birthdate, Adress, Number, PostalCode, City, Email, Active, Salt) values " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, trader.getID());
        ps.setString(2, trader.getPassword());
        ps.setString(3, trader.getFirstName());
        ps.setString(4, trader.getPrefix());
        ps.setString(5, trader.getName());
        ps.setInt(6, trader.getBSN());
        ps.setString(7, trader.getDateOfBirth());
        ps.setString(8, trader.getStreet());
        ps.setString(9, trader.getHouseNumber());
        ps.setString(10, trader.getZipCode());
        ps.setString(11, trader.getCity());
        ps.setString(12, trader.getEmail());
        ps.setBoolean(13, trader.isActive());
        ps.setString(14, trader.getSalt());
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
    public Trader findById(int id) {
        String sql = "SELECT * FROM Trader WHERE idTrader = ?";
        List<Trader> resultList = jdbcTemplate.query(sql, new TraderRowMapper(), id);
        if (resultList.size() == 0) {
            return null;
        } else {
            return resultList.get(0);
        }
    }


    @Override
    public List<Trader> getTraderByName(String name) {
        String sql = "SELECT * FROM Trader WHERE Name = ?";
        return jdbcTemplate.query(sql, new TraderRowMapper(), name);
    }

    @Override
    public void update(Trader trader) {
        jdbcTemplate.update("UPDATE Trader SET Password = ?, FirstName = ?, Prefix = ?, Name = ?, BSN = ?, Birthdate = ?, Adress = ?," +
                        "Number = ?, PostalCode = ?, City = ?, Email = ?, Active = ? WHERE idTrader = ?", trader.getPassword(),
                trader.getFirstName(), trader.getPrefix(), trader.getName(), trader.getBSN(), trader.getDateOfBirth(),
                trader.getStreet(), trader.getHouseNumber(), trader.getZipCode(), trader.getCity(), trader.getEmail(),
                trader.isActive(), trader.getID());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Trader WHERE idTrader = ?";
        jdbcTemplate.update(sql, id);
    }

    private class TraderRowMapper implements RowMapper<Trader> {
        @Override
        public Trader mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
            return new Trader(resultSet.getInt("idTrader"), resultSet.getString("Email"), resultSet.getString("Password"),
                    resultSet.getString("FirstName"), resultSet.getString("Prefix"), resultSet.getString("Name"),
                    resultSet.getInt("BSN"), resultSet.getString("Birthdate"), resultSet.getString("Adress"),
                    resultSet.getString("Number"), resultSet.getString("PostalCode"), resultSet.getString("City"),
                    resultSet.getBoolean("Active"), resultSet.getString("Salt"));
        }
    }


}
