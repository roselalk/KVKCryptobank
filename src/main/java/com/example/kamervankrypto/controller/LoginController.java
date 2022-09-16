package com.example.kamervankrypto.controller;

import com.example.kamervankrypto.config.BadCredentialsException;
import com.example.kamervankrypto.model.Login;
import com.example.kamervankrypto.service.LoginService;
import com.example.kamervankrypto.utils.ExceptionMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @PostMapping
    public ResponseEntity<?> login(@RequestBody Login login) throws SQLException {
        String salt = loginService.getSalt(login.getEmail());
        String hashedPassword = loginService.hashPassword(login.getPassword(), salt);
        if (loginService.loginDetailsCorrect(login.getEmail(), hashedPassword)) {
            return ResponseEntity.ok(ExceptionMessage.LOGIN_SUCCESS.toString());
        } else {
            throw new BadCredentialsException(ExceptionMessage.INVALID_LOGIN_CREDENTIALS.toString());
        }

    }






}
