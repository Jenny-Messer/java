// Copyright (c) 2018 Travelex Ltd

public class Customer extends User{

    private int balance; //int?

    public Customer(int balance, int pin, int customerNumber){
        super(pin, customerNumber, false);
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }




}
