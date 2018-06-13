// Copyright (c) 2018 Travelex Ltd

import java.util.Map;

public class Customer extends User {

    private int balance; //int?

    public Customer(int balance, int pin, int customerNumber){
        super(pin, customerNumber);
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void updateBalance(Map<Integer, Integer> removedNotes){

        //not sure what magic intellij did here

        int totalToRemoveFromBalance = removedNotes.keySet().stream().mapToInt(removedNotes::get).sum();

        //this.setBalance(this.getBalance() - totalToRemoveFromBalance);
        balance -= totalToRemoveFromBalance;
    }


}
