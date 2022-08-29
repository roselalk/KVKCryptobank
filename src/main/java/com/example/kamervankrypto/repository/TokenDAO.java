package com.example.kamervankrypto.repository;


public interface TokenDAO {

    default void saveToken(int id, String token){
    }

    default void updateToken(int id){
    }

    default Boolean checkTokenValidity(String token){
        Boolean validity = false;
        return validity;
    }

    default int getTraderIDFromToken(String token){
        return 0;
    }
}
