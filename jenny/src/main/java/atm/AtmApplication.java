package atm;// Copyright (c) 2018 Travelex Ltd

import atm.model.ATM;
import atm.model.AustralianATM;
import atm.model.BankEmployee;
import atm.model.Customer;
import atm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import atm.service.AtmService;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan
public class AtmApplication implements CommandLineRunner {

    private static AtmService atmService;

    @Autowired
    public AtmApplication(AtmService atmService){
        AtmApplication.atmService = atmService;
    }

    public static void main(String... args) {
        SpringApplication.run(AtmApplication.class, args);
    }

    @Override
    public void run(String... args) {


        Map<Integer, Integer> contents = new HashMap<>();
        contents.put(10, 10);
        contents.put(20, 20);
        contents.put(50, 50);
        contents.put(100, 100);


        ATM atm = new AustralianATM(contents);

        Boolean exit = false;

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
