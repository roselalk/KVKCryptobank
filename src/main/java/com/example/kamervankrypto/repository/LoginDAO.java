package com.example.kamervankrypto.repository;

public interface LoginDAO {

    boolean checkPasswordRequirements(String password);
    String generateSalt();
    String getSalt(String email);
    String hashPassword(String password, String salt);
    boolean loginDetailsCorrect(String email, String password);


}
