package model;// Copyright (c) 2018 Travelex Ltd

import service.ExchangeServiceImpl;
import service.UserDataAccessImpl;

import java.math.BigDecimal;
import java.util.Map;

public class Customer extends User {

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    private BigDecimal balance; //int?

    private String currency;

    public Customer(BigDecimal balance, int pin, int customerNumber, String currency){
        super(pin, customerNumber);
        this.balance = balance;
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }


    //public void updateBalance(Map<Integer, Integer> removedNotes){

        //moved}



}
