package com.example.kamervankrypto.controller;

import com.example.kamervankrypto.model.Trader;
import com.example.kamervankrypto.service.LoginService;
import com.example.kamervankrypto.service.TraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/traders")
public class TraderController {

    private TraderService traderService;
    private LoginService loginService;

    @Autowired
    public TraderController(TraderService traderService, LoginService loginService) {
        this.traderService = traderService;
        this.loginService = loginService;
    }

    @GetMapping
    @ResponseBody
    List<Trader> getTraders() {
        return traderService.getAll();
    }

    //Full path: {localhost:8080}/traders/user/{id}
    @GetMapping(value = "/users/{id}")
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
    @GetMapping (value = "/users/find")
    @ResponseBody
    List<Trader> getTraderByName(@RequestParam("traderName") String name) {
        return traderService.getByName(name);
    }

    @PutMapping(value = "/register")
    @ResponseBody
    ResponseEntity<String> createTrader(@RequestBody Trader trader) {
        if (loginService.checkPasswordRequirements(trader.getPassword())) {
            //Generate Salt
            String salt = loginService.generateSalt();
            //Hash het password+salt
            String hashedPassword = loginService.hashPassword(trader.getPassword(), salt);
            //Stel de hash in als password
            trader.setPassword(hashedPassword);
            //Stel de salt in
            trader.setSalt(salt);
            //Sla de trader op, dus met hash ipv opgegeven password
            traderService.save(trader);
            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/traders/register").toUriString());
            return ResponseEntity.created(uri).body("Registratie succesvol");
        } else {
            return ResponseEntity.ok("Je wachtwoord moet uit minimaal 8 karakters bestaan.");
        }
    }

    //Zet alle gegevens van de Trader die je wil wijzigen in de body
    @PostMapping ("/users/update")
    public ResponseEntity<Trader> updateTrader(@RequestBody Trader trader) {
        traderService.upate(trader);
        return ResponseEntity.ok(trader);
    }

    //Zet het ID van de Trader die je wil verwijderen in de path (dus na /delete). Hoeft niks in de body
    @DeleteMapping ("/users/delete/{id}")
    public ResponseEntity<Trader> deleteTrader(@PathVariable(value = "id") int ID) {
        Trader trader = traderService.getById(ID);
        traderService.delete(ID);
        return ResponseEntity.ok(trader);
    }



}
