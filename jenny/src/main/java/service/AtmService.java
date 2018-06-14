package service;// Copyright (c) 2018 Travelex Ltd

import exceptions.LoginFailedException;
import exceptions.NotEnoughCashException;
import exceptions.UserNotFoundException;
import model.ATM;
import model.Customer;
import model.User;
import model.Withdrawal;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class AtmService {

    private UserDataAccess userDataAccess;

    private ATM atm;

    public AtmService(ATM atm, UserDataAccess userDataAccess) {
        this.atm = atm;
        this.userDataAccess = userDataAccess;
    }

    public Map<Integer , Integer> getAtmContents() {
        return atm.getContents();
    }


    public void withdraw(ATM atm, Customer customer){
        BigDecimal requestedAmount = BigDecimal.ZERO;

        System.out.println("\nEnter amount to withdraw: ");
        Scanner scanner = new Scanner(System.in);


        //<= for BigDecimal
        requestedAmount = validPositiveInput(requestedAmount);

        //check if user's currency is same as ATM currency

        ExchangeServiceImpl exchangeService = new ExchangeServiceImpl(userDataAccess);

        if (!customer.getCurrency().equals(atm.getCurrency())){

            BigDecimal exchangedRequestedAmount =
                    exchangeService.findRightConversion(requestedAmount, customer.getCurrency(), atm.getCurrency());

            System.out.println("Your currency is " + customer.getCurrency()
                                       + ", this ATM dispenses " + atm.getCurrency() + ". \n"
                                       + "You requested " + requestedAmount
                                       + "  the ATM will attempt to dispense  " + exchangedRequestedAmount + ".\n"
                                       + "if you want to withdraw a different amount in " + atm.getCurrency()
                                       + ", enter y \n"
                                       + "to continue with initial withdrawal request enter n.");

            String withdrawDifferentAmount = scanner.nextLine();

            if (withdrawDifferentAmount.charAt(0) == 'y'){

                requestedAmount = new BigDecimal(0);
                System.out.println("Enter value to withdraw in " + atm.getCurrency() + " :");

                requestedAmount = validPositiveInput(requestedAmount);
                //this requested amount is in atm currency
                BigDecimal amountToRemove = exchangeService.findRightConversion(requestedAmount, atm.getCurrency(), customer.getCurrency());

                checkBalanceEnoughToWithdraw(amountToRemove, customer);

            }
            else{
                requestedAmount = exchangedRequestedAmount;
                checkBalanceEnoughToWithdraw(requestedAmount, customer);
            }


            try {
                Withdrawal atmWithdrawal = atm.validDispense(requestedAmount, customer);

                exchangeService.getValueToRemoveFromBalanceInCorrectCurrency(atm.getCurrency(), customer.getUserNumber(),atmWithdrawal.getContents() );

                System.out.println("Withdraw successful \nyou withdrew :");

                atmWithdrawal.getContents().keySet()
                             .forEach(noteSize -> System.out.println(atmWithdrawal.getContents().get(noteSize) + " " + noteSize + " dollar note(s), "));

            } catch (NotEnoughCashException e) {
                System.out.println("Not enough cash in ATM");
            }


        }


    }

    public BigDecimal validPositiveInput(BigDecimal requestedAmount){
        Scanner scanner = new Scanner(System.in);

        //<= for BigDecimal
        while (requestedAmount.compareTo( new BigDecimal(0)) == 0 || requestedAmount.compareTo( new BigDecimal(0)) == 0){

            try {
                requestedAmount = scanner.nextBigDecimal();

            } catch (InputMismatchException e) {
                System.out.println("Enter valid input");
            }

            if (requestedAmount.compareTo( new BigDecimal(0)) == 0 || requestedAmount.compareTo( new BigDecimal(0)) == 0){
                System.out.println("Enter a positive number");
            }

        }

        return requestedAmount;
    }

    //terrible name
    public void checkBalanceEnoughToWithdraw(BigDecimal withdrawAmount, Customer customer){

        if (0 < withdrawAmount.compareTo(customer.getBalance())) {
            throw new NotEnoughCashException();
        }

    }

    public void checkBalance(Customer customer){

        System.out.println("The balance for account " + customer.getUserNumber() + " is $" + customer.getBalance());

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

        //user types 10 5 to put 5 10s in the model.ATM
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
