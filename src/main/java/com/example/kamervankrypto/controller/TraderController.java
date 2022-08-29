package com.example.kamervankrypto.controller;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
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
    Trader getTraderById(@PathVariable("id") int id) {
        Optional<Trader> trader = Optional.ofNullable(traderService.getById(id));
        if (trader.isPresent()) {
            return trader.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No trader found with this ID!");
        }
    }

    //Full path: {localhost:8080}/traders/find?traderName={starr}
    //(In Postman: vul in tot en met find, en voeg in de Params toe traderName en de (achter)naam die je wil zoeken
    @GetMapping (value = "/user/find")
    @ResponseBody
    List<Trader> getTraderByName(@RequestParam("traderName") String name) {
        return traderService.getByName(name);
    }

    @PutMapping
    @ResponseBody
    List<Trader> createTrader(@RequestBody Trader trader) {
        traderService.save(trader);
        return traderService.getAll();
    }

    //Zet alle gegevens van de Trader die je wil wijzigen in de body
    @PostMapping ("/user/update")
    public ResponseEntity<Trader> updateTrader(@RequestBody Trader trader) {
        traderService.upate(trader);
        return ResponseEntity.ok(trader);
    }

    //Zet het ID van de Trader die je wil verwijderen in de path (dus na /delete). Hoeft niks in de body
    @DeleteMapping ("/user/delete/{id}")
    public ResponseEntity<Trader> deleteTrader(@PathVariable(value = "id") int ID) {
        Trader trader = traderService.getById(ID);
        traderService.delete(ID);
        return ResponseEntity.ok(trader);
    }


}
