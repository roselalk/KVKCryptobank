package com.example.kamervankrypto.model;

public class Login {

    //Login class aangemaakt omdat er anders in JSON geen onderscheid kon worden gemaakt tussen de Strings email en
    //password, waardoor bij het inloggen email+password als email werden gezien.

    private String email;
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
