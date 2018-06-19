package atm.model;// Copyright (c) 2018 Travelex Ltd

public abstract class User {

    private int userNumber;
    private int pin;

    public User(int pin, int userNumber) {
        this.pin = pin;
        this.userNumber = userNumber;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

}
