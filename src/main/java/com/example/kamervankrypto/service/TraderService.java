package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.repository.Trader.TraderDAO;
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

    public Trader getById(int id) {
        return traderDAO.findById(id);
    }

    public List<Trader> getByName(String name) {
        return traderDAO.getTraderByName(name);
    }

    public void save(Trader trader) {
        traderDAO.save(trader);
    }

    public void upate(Trader trader) {
        traderDAO.update(trader);
    }

    public void delete(int id) {
        traderDAO.delete(id);
    }



}
