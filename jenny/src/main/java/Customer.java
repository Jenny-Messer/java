// Copyright (c) 2018 Travelex Ltd

import java.util.HashMap;
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


        for (Map.Entry<Integer, Integer> noteEntry : removedNotes.entrySet()) {
            balance -= noteEntry.getKey() * noteEntry.getValue();
        }

    }


}
