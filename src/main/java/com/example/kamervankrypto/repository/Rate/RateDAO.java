package com.example.kamervankrypto.repository.Rate;

import com.example.kamervankrypto.model.Rate;

import java.util.List;

public interface RateDAO {


    List<Rate> getAllByTicker(String ticker);

    Rate getCurrentByTicker(String ticker);

    void store(Rate rate);
}
