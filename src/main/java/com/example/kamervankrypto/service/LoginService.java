package com.example.kamervankrypto.service;

import com.example.kamervankrypto.repository.Login.LoginDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class LoginService {

    private LoginDAO loginDAO;

    public LoginService(LoginDAO loginDAO) {
        this.loginDAO = loginDAO;
    }

    public boolean checkPasswordRequirements(String password) {
        return loginDAO.checkPasswordRequirements(password);
    }

    public String generateSalt() {
        return loginDAO.generateSalt();
    }

    public String getSalt(String email) {
        return loginDAO.getSalt(email);
    }

    public String hashPassword(String password, String salt) {
        return loginDAO.hashPassword(password, salt);
    }

    public boolean loginDetailsCorrect(String email, String password) throws SQLException {
        return loginDAO.loginDetailsCorrect(email, password);
    }


}
