package com.example.kamervankrypto.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Rate {

    int idRate;
    double value;
    LocalDateTime date;
    String ticker;

    public Rate(int idRate, double value, LocalDateTime date) {
        this.idRate = idRate;
        this.value = value;
        this.date = date;
    }
}
