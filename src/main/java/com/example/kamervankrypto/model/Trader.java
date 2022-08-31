package com.example.kamervankrypto.model;

import java.util.Objects;

public class Trader {
    private int ID;
    private String email;
    private String password;
    private String salt;
    private String firstName;
    private String prefix;
    private String name;
    private int BSN;
    private String dateOfBirth;
    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private boolean isActive;


    public Trader(int ID, String email, String password, String firstName, String prefix, String name, int BSN,
                  String dateOfBirth, String street, String houseNumber, String zipCode, String city, boolean isActive, String salt) {
        this.ID = ID;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.prefix = prefix;
        this.name = name;
        this.BSN = BSN;
        this.dateOfBirth = dateOfBirth;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.isActive = isActive;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBSN() {
        return BSN;
    }

    public void setBSN(int BSN) {
        this.BSN = BSN;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {return firstName + " " + prefix + " " + name + ", ID " + ID;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trader trader = (Trader) o;
        return ID == trader.ID && name.equals(trader.name);
    }
//Is dit nodig?
//    @Override
//    public int hashCode() {
//        return Objects.hash(ID, name);
//    }

}
