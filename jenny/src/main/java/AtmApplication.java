// Copyright (c) 2018 Travelex Ltd

import exceptions.NotEnoughCashException;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class AtmApplication {

    public static void main(String[] args){


        Map<Integer, Integer> contents = new HashMap<Integer, Integer>();
        contents.put(10, 10);
        contents.put(20, 20);
        contents.put(50, 50);
        contents.put(100, 100);

        ATM atm = new AustralianATM(contents);

        Boolean exit = false;
        int requestedAmount = 0;

        while(!exit) {
            System.out.println("Enter amount to withdraw: ");
            Scanner scanner = new Scanner(System.in);
            try {
                requestedAmount = scanner.nextInt();
                System.out.println(requestedAmount);

            } catch (InputMismatchException e) {
                System.out.println("Enter valid input");
            }

            try {
                atm.validDispense(requestedAmount);
            } catch (NotEnoughCashException e) {
                System.out.println("soz you cant get we dont have enough...");
            }

        }
    }
}
