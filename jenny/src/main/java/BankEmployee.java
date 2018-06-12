// Copyright (c) 2018 Travelex Ltd

public class BankEmployee extends User {

    public BankEmployee(int balance, int pin, int customerNumber){
        super(pin, customerNumber, true);
    }
}
