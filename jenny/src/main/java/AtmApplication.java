// Copyright (c) 2018 Travelex Ltd

import exceptions.NotEnoughCashException;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class AtmApplication {

    public static void main(String[] args){


        Map<Integer, Integer> contents = new HashMap<Integer, Integer>();

        contents = initAtm(contents);

        ATM atm = new AustralianATM(contents);

        Boolean exit = false;
        Scanner scanner = new Scanner(System.in);


        while(!exit) {

            System.out.println("Withdraw(1), Add a new type of note(2), refill ATM with original contents(3) or exit(3)? :");
            int withdrawOrRefill = scanner.nextInt();

            switch (withdrawOrRefill){
                case 1: withdraw(atm);
                case 2: contents = addNewNoteToAtm(contents);
                case 3: contents = initAtm(contents);
                case 4: exit = true;
            }

        }
    }

    public static void withdraw(ATM atm){
        int requestedAmount = 0;

        System.out.println("\nEnter amount to withdraw: ");
        Scanner scanner = new Scanner(System.in);
        try {
            requestedAmount = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("Enter valid input");
        }

        try {
            Withdrawal atmWithdrawal = atm.validDispense(requestedAmount);

            System.out.println("Withdraw successful \nyou withdrew :");

            atmWithdrawal.getContents().keySet()
                         .forEach(noteSize -> System.out.println(atmWithdrawal.getContents().get(noteSize) + " " + noteSize + " dollar note(s), "));

        } catch (NotEnoughCashException e) {
            System.out.println("Not enough cash in ATM");
        }
    }

    public static Map<Integer, Integer> addNewNoteToAtm(Map<Integer, Integer> contents){

        //user types 10 5 to put 5 10s in the ATM
        //input Done when all note types entered

        //new note types?

        System.out.println("\nEnter NOTE SIZE and NUMBER OF NOTES of that size. "
                                   + "\nWhen done, type 'Done'"
                                   + "\nE.g. to enter 5 $10 notes type : 10 5");

        Scanner scanner = new Scanner(System.in);
        int noteKey = scanner.nextInt();
        int noteVolume = scanner.nextInt();

        System.out.println(noteKey + " " + noteVolume);

        contents.put(noteKey,noteVolume);

        return contents;
    }



    public static Map<Integer, Integer> initAtm(Map<Integer, Integer> contents){
        contents.put(10, 10);
        contents.put(20, 20);
        contents.put(50, 50);
        contents.put(100, 100);

        return contents;
    }

}
