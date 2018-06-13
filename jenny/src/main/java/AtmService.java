// Copyright (c) 2018 Travelex Ltd

import exceptions.LoginFailedException;
import exceptions.NotEnoughCashException;
import exceptions.UserNotFoundException;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class AtmService {

    private UserDataAccess userDataAccess;

    private ATM atm;

    public AtmService(ATM atm) {
        this.atm = atm;
        userDataAccess = new UserDataAccess();
    }

    public Map<Integer , Integer> getAtmContents() {
        return atm.getContents();
    }


    public void withdraw(ATM atm, Customer customer){
        int requestedAmount = 0;

        System.out.println("\nEnter amount to withdraw: ");
        Scanner scanner = new Scanner(System.in);

        try {
            requestedAmount = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("Enter valid input");
        }

        try {
            Withdrawal atmWithdrawal = atm.validDispense(requestedAmount, customer);

            System.out.println("Withdraw successful \nyou withdrew :");

            atmWithdrawal.getContents().keySet()
                         .forEach(noteSize -> System.out.println(atmWithdrawal.getContents().get(noteSize) + " " + noteSize + " dollar note(s), "));

        } catch (NotEnoughCashException e) {
            System.out.println("Not enough cash in ATM");
        }
    }

    public void checkBalance(Customer customer){



    }


    public User login(int userId, int pin) {
        Optional<User> userOpt = userDataAccess.getUser(userId);
        User user = userOpt.orElseThrow(UserNotFoundException::new);

        if (user.getPin() != pin) {
            throw new LoginFailedException();
        }

        return user;

    }

    public Map<Integer, Integer> addNewNoteToAtm(Map<Integer, Integer> contents){

        //user types 10 5 to put 5 10s in the ATM
        //input Done when all note types entered

        //new note types?

        System.out.println("\nEnter NOTE SIZE and NUMBER OF NOTES of that size. "
                                   + "\nWhen done, type 'Done'"
                                   + "\nE.g. to enter 5 $10 notes type : 10 5");

        Scanner scanner = new Scanner(System.in);

        int noteKey = 0;
        int noteVolume = 0;

        try {
            noteKey = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("Invalid note type");
        }

        try {
            noteVolume = scanner.nextInt();

        } catch (InputMismatchException e) {
            System.out.println("Invalid volume of notes");
        }

        System.out.println(noteKey + " " + noteVolume);

        contents.put(noteKey,noteVolume);

        return contents;
    }







}
