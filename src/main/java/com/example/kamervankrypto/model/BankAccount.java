package com.example.kamervankrypto.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

public class BankAccount {

    private Trader trader;
    private double saldo;
//    private String saldoDateTime;
    private LocalDateTime saldoDateTime;
    private String iban;
    //private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");


    // CONSTRUCTORS

    public BankAccount(Trader trader, double saldo, LocalDateTime saldoDateTime, String iban) {
        this.trader = trader;
        this.saldo = saldo;
        this.saldoDateTime = saldoDateTime;
        this.iban = iban;
    }

    public BankAccount(Trader trader, double saldo) {
        this.trader = trader;
        this.saldo = saldo;
        this.saldoDateTime = getCurrentDateTime();
        this.iban = createNewIBAN();
    }



    public BankAccount(double saldo, LocalDateTime saldoDateTime, String iban) {
        this.saldo = saldo;
        this.saldoDateTime = saldoDateTime;
        this.iban = iban;
        this.trader = null;
    }

    // METHODS

    public static LocalDateTime getCurrentDateTime() {
        LocalDateTime dateTimeNow = LocalDateTime.now();
        System.out.println(dateTimeNow);

        // EEN KORTE TEST OM MET LOCALDATETIME TE REKENEN. DEZE METHODE WERKT
//        LocalDateTime BirthDate = LocalDateTime.of(1984, 4, 25, 10, 10, 25);
//        String sBirthDate = "1984-08-26T13:24:33.285401";
//
//        if (dateTimeNow.isAfter(BirthDate) ){
//            System.out.println("Jup, erna");
//        } else {
//            System.out.println("Jup, ervoor");
//        }

        return LocalDateTime.parse(dateTimeNow.format(DATEFORMAT), DATEFORMAT);
    }


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
                "trader=" + trader +
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
        return Double.compare(that.saldo, saldo) == 0 && trader.equals(that.trader) && saldoDateTime.equals(that.saldoDateTime) && iban.equals(that.iban);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trader, saldo, saldoDateTime, iban);
    }

// GETTERS AND SETTERS


    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDateTime getSaldoDateTime() {
        return saldoDateTime;
    }

    public void setSaldoDateTime(LocalDateTime saldoDateTime) {
        this.saldoDateTime = saldoDateTime;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public static DateTimeFormatter getDATEFORMAT() {
        return DATEFORMAT;
    }
}
