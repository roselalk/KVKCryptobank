package com.example.kamervankrypto.utils;

public enum ExceptionMessage {
    INVALID_TOKEN("Invalid token!"),
    INVALID_LOGIN_CREDENTIALS("Email en wachtwoord matchen niet!"),
    LOGIN_SUCCESS("Login succesvol"),
    TRADER_NOT_FOUND("Trader not found!"),
    ASSET_NOT_FOUND("Asset not found!"),
    BANK_ACCOUNT_NOT_FOUND("Bank account not found!"),
    PORTFOLIO_NOT_FOUND("Portfolio not found!"),
    WALLET_NOT_FOUND("Wallet not found!"),
    TRANSACTION_NOT_FOUND("Transaction not found!");


    private final String exceptionMessageAsString;

    //constructor:
    ExceptionMessage(String message) {
        this.exceptionMessageAsString = message;
    }

    @Override
    public String toString() {
        return exceptionMessageAsString;
    }
    }


