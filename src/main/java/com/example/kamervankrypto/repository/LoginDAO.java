package com.example.kamervankrypto.repository;

import java.sql.SQLException;

public interface LoginDAO {

    boolean checkPasswordRequirements(String password);
    String generateSalt();
    String getSalt(String email);
    String hashPassword(String password, String salt);
    boolean loginDetailsCorrect(String email, String password) throws SQLException;


}
