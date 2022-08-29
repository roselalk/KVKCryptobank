package com.example.kamervankrypto.service;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.repository.TraderDAO;
import org.springframework.stereotype.Service;

import java.io.Serializable;
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

    public Trader getByName(String name) {
        return traderDAO.getTraderByName(name);
    }

    public void save(Trader trader) {
        traderDAO.save(trader);
    }

    public void upate(Trader trader) {
        traderDAO.update(trader);
    }

    public void delete(int ID) {
        traderDAO.delete(ID);
    }



}
