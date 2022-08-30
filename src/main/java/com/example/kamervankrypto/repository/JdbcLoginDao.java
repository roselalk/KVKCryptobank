package com.example.kamervankrypto.repository;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

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
        String sql = "SELECT Salt FROM Trader WHERE Email = ?";
        return jdbcTemplate.queryForObject(sql, String.class, email);
    }

    @Override
    public String hashPassword(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    public boolean loginDetailsCorrect(String email, String password) {
        //check of email bestaat in db
        String sql = "SELECT Password FROM Trader WHERE Email = ?";
        String foundPassword = jdbcTemplate.queryForObject(sql, String.class, email);
        if (foundPassword == null) {
            //TODO oplossen wat er gebeurt als de email niet in de db wordt gevonden
            System.out.println("Geen wachtwoord gevonden");
            return false;
        } else {
            //check of ww in db en opgegeven ww hetzelfde zijn
            return password.equals(foundPassword);
        }
    }
}
