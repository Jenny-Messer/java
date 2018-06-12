// Copyright (c) 2018 Travelex Ltd

public class User {

    private int pin;
    private int customerNumber;

    public boolean isRefillAtmAccess() {
        return refillAtmAccess;
    }

    private boolean refillAtmAccess;

    public User(int pin, int customerNumber, boolean refillAtmAccess) {
        this.pin = pin;
        this.customerNumber = customerNumber;
        this.refillAtmAccess = refillAtmAccess;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

}
