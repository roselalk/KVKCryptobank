package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.repository.TraderDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraderService {

    private TraderDAO traderDAO;

    public TraderService(TraderDAO traderDAO) {
        this.traderDAO = traderDAO;
    }

    public List<Trader> getAll() {
        return traderDAO.findAll();
    }

    public Trader getById(String id) {
        return traderDAO.findById(id);
    }

    public void save(Trader trader) {
        traderDAO.save(trader);
    }

    public Trader getByName(String name) {
        return traderDAO.getTraderByName(name);
    }

    //TODO method fillDatabase (generate random users)


}
