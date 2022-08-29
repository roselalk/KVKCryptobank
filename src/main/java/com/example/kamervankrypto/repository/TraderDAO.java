package com.example.kamervankrypto.repository;

import com.example.kamervankrypto.model.Trader;

import java.util.List;

public interface TraderDAO {

    List<Trader> findAll();

    Trader findById(String id);

    void update(Trader trader);

    void save(Trader trader);

    Trader getTraderByName(String name);

    void delete(int id);
}
