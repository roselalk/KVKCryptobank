package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.BankAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public class JdbcTokenDAO implements TokenDAO{
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTokenDAO (JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveToken(int id, String token){ // token wordt opgeslagen als parameters, Token heeft geen model klasse
        String sql = "INSERT INTO Token (idTrader, Token, ValidUntil) VALUES (?,?,?)";
        String validUntil = BankAccount.getCurrentDateTime().plusMinutes(15).toString();  // FOR NOW 15 MINUTES ACCESS

        System.out.println(validUntil);  // een Println om te kunnen verifieren tijdens test.

        jdbcTemplate.update(sql, id, token, validUntil);
    }

    @Override
    public void updateToken(int id){
        // TODO. Hier moet nog wat voor verzonnen worden.
    }

    @Override
    public Boolean checkTokenValidity(String token){
        LocalDateTime now = BankAccount.getCurrentDateTime();

        String sql = "Select ValidUntil from Token Where Token = ?";
        String validUntilString = jdbcTemplate.queryForObject(sql, String.class, token);
        assert validUntilString != null;
        LocalDateTime validUntil = LocalDateTime.parse(validUntilString);

        if (now.isBefore(validUntil)){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public int getTraderIDFromToken(String token){ // aanname token is altijd uniek
        String sql = "Select idTrader from Token Where Token = ?";

        int idTrader = 0;
        try{
            idTrader = jdbcTemplate.queryForObject(sql, Integer.class, token);
        }catch (NullPointerException ex){
            System.out.println("No trader linked to this token");
        }

        return idTrader;
    }

}
