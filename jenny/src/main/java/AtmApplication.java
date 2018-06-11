// Copyright (c) 2018 Travelex Ltd

import java.util.InputMismatchException;
import java.util.Scanner;

public class AtmApplication {

    public static void main(String[] args){
        ATM atm = new ATM(null);

        Boolean exit = false;

        while(!exit) {
            System.out.println("Enter amount to withdraw: ");
            Scanner scanner = new Scanner(System.in);
            try {
                int requestedAmount = scanner.nextInt();
                System.out.println(requestedAmount);

            } catch (InputMismatchException e) {
                System.out.println("Enter valid input");
            }

            atm




        }
    }
}
