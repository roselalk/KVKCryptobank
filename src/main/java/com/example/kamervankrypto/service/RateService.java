package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.Rate;
import com.example.kamervankrypto.repository.Rate.RateDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService {

    private RateDAO rateDAO;

    public RateService(RateDAO rateDAO) {
        this.rateDAO = rateDAO;
    }

    public List<Rate> getAllByTicker(String ticker) {
        return rateDAO.getAllByTicker(ticker);
    }

    public void store(Rate rate) {
        rateDAO.store(rate);
    }

}
