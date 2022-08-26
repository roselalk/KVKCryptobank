package com.example.kamervankrypto.controller;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/traders")
public class TraderController {

    private TraderService traderService;

    @Autowired
    public TraderController(TraderService traderService) {
        this.traderService = traderService;
    }

    @GetMapping
    @ResponseBody
    List<Trader> getTraders() {
        return traderService.getAll();
    }

    //Full path: {localhost:8080}/traders/user/{id}
    @GetMapping(value = "/user/{id}")
    @ResponseBody
    Trader getTraderById(@PathVariable("id") String id) {
        Optional<Trader> trader = Optional.ofNullable(traderService.getById(id));
        if (trader.isPresent()) {
            return trader.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trader found with this ID!");
        }
    }

    @PostMapping
    @ResponseBody
    List<Trader> createTrader(@RequestBody Trader trader) {
        traderService.save(trader);
        return traderService.getAll();
    }

    @PostMapping(value = "/delete")
    @ResponseBody
    List<Trader> deleteTrader(@RequestBody int ID) {
        traderService.delete(ID);
        return traderService.getAll();
    }

    //Full path: {localhost:8080}/traders/find?traderName={starr}
    //(In Postman: vul in tot en met find, en voeg in de Params toe traderName en de (achter)naam die je wil zoeken
    @GetMapping (value = "/find")
    @ResponseBody
    Trader getTraderByName(@RequestParam("traderName") String name) {
        return traderService.getByName(name);
    }


}
