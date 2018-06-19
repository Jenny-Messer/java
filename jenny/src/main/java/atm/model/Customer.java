package atm.model;// Copyright (c) 2018 Travelex Ltd

public class Customer extends User {

    private Account account;

    public Customer(int pin, int customerNumber, Account account) {
        super(pin, customerNumber);

        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

}
