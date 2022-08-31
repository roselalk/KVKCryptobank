package com.example.kamervankrypto.repository;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.SQLWarningException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.sql.SQLWarning;

@Repository
public class JdbcLoginDao implements LoginDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcLoginDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public boolean checkPasswordRequirements(String password) {
        return password.length() >= 8;
    }

    public String generateSalt() {
        return BCrypt.gensalt();
    }

    public String getSalt(String email) {
        String salt = BCrypt.gensalt();
        try {
            String sql = "SELECT Salt FROM Trader WHERE Email = ?";
            salt = jdbcTemplate.queryForObject(sql, String.class, email);
        } catch (EmptyResultDataAccessException emailNotFound) {
            System.out.println("SQL Fout in JdbcLoginDAO.getSalt: " + emailNotFound.getMessage());
        }
        return salt;
    }

    @Override
    public String hashPassword(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    public boolean loginDetailsCorrect(String email, String password) {
        try {
            String sql = "SELECT Password FROM Trader WHERE Email = ?";
            String foundPassword = jdbcTemplate.queryForObject(sql, String.class, email);
            return password.equals(foundPassword);
        } catch (EmptyResultDataAccessException emailNotFound) {
            System.out.println("SQL Fout in JdbcLoginDAO.loginDetailsCorrect: " + emailNotFound.getMessage());
        }
        return false;
    }
}
