// Copyright (c) 2018 Travelex Ltd

import model.ATM;
import model.AustralianATM;
import model.BankEmployee;
import model.Customer;
import model.User;
import service.AtmService;
import service.UserDataAccessImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AtmApplication {

    private static UserDataAccessImpl userDataAccess = new UserDataAccessImpl();

    private static AtmService atmService;

    public static void setExit(Boolean exit) {
        AtmApplication.exit = exit;
    }

    private static Boolean exit = false;

    public static void main(String[] args) {

        Map<Integer, Integer> contents = new HashMap<>();
        contents.put(10, 10);
        contents.put(20, 20);
        contents.put(50, 50);
        contents.put(100, 100);


        ATM atm = new AustralianATM(contents);

        atmService = new AtmService(atm, userDataAccess);

        exit = false;

        Scanner scanner = new Scanner(System.in);

        boolean login = true;

        while (!exit && login) {

            System.out.println("Login: \nEnter Customer Number: ");
            int enteredCustomerNumber = scanner.nextInt();
            System.out.println("Enter Pin: ");
            int enteredPin = scanner.nextInt();

            User user = atmService.login(enteredCustomerNumber, enteredPin);

            if (user instanceof Customer) {
                System.out.println("Withdraw(1), check balance (2), or exit? :");
                int withdrawOrRefill = scanner.nextInt();

                switch (withdrawOrRefill){
                    case 1: atmService.withdraw(atm, (Customer) user); break;
                    case 2: atmService.checkBalance((Customer) user); break;
                    default: exit = true;
                }

            } else if (user instanceof BankEmployee) {
                System.out.println("Add a new type of note(1), or exit? :");
                int withdrawOrRefill = scanner.nextInt();

                switch (withdrawOrRefill){
                    case 1: atmService.addNewNoteToAtm(contents); break;
                    default: exit = true;
                }
            }



        }

    }


}
