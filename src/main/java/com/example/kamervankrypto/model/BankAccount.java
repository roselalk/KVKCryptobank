package com.example.kamervankrypto.model;

import java.util.Objects;
import java.util.Random;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

public class BankAccount {

    private int idTrader;
    private double saldo;
    private String saldoDateTime;
    private String iban;

    // CONSTRUCTORS

    public BankAccount(int idTrader, double saldo, String saldoDateTime, String iban) {
        this.idTrader = idTrader;
        this.saldo = saldo;
        this.saldoDateTime = saldoDateTime;
        this.iban = iban;
    }

    public BankAccount(int idTrader, double saldo) {
        this.idTrader = idTrader;
        this.saldo = saldo;
        this.saldoDateTime = DateTime.toString();
        this.iban = createNewIBAN();
    }


    // METHODS

    private String createNewIBAN(){
        String Countrycode  = "NL";
        String BankCode = "KVKB";

        String IBAN1 = Countrycode + RandomNumberGenerator() + RandomNumberGenerator() + BankCode;
        StringBuilder IBAN2 = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            IBAN2.append(RandomNumberGenerator());
        }
        System.out.println(IBAN1 + IBAN2);

        return  IBAN1 + IBAN2;
    }

    private int RandomNumberGenerator(){
        Random random = new Random();
        return random.nextInt(9);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "idTrader=" + idTrader +
                ", saldo=" + saldo +
                ", saldoDateTime='" + saldoDateTime + '\'' +
                ", iban='" + iban + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return idTrader == that.idTrader && Double.compare(that.saldo, saldo) == 0 && saldoDateTime.equals(that.saldoDateTime) && iban.equals(that.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTrader, saldo, saldoDateTime, iban);
    }


    // GETTERS AND SETTERS

    public int getIdTrader() {
        return idTrader;
    }

    public void setIdTrader(int idTrader) {
        this.idTrader = idTrader;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getSaldoDateTime() {
        return saldoDateTime;
    }

    public void setSaldoDateTime(String saldoDateTime) {
        this.saldoDateTime = saldoDateTime;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}
