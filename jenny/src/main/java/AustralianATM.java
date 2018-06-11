// Copyright (c) 2018 Travelex Ltd

import exceptions.NotEnoughCashException;

import java.util.HashMap;
import java.util.Map;

public class AustralianATM implements ATM {

    //TODO error when notes cannot be dispensed in full
    //TODO what are the contents of the atm
    //TODO let user add and take out money
    //TODO system for finding best combo of notes to dispense

    private Map<Integer , Integer> contents;

    public AustralianATM(Map<Integer, Integer> contents) {
        this.contents = contents;
    }

    public Map<Integer, Integer> getContents() {
        return contents;
    }

    public boolean validDispense(int withdrawAmmount) throws NotEnoughCashException {

        //checks if the money the user wants to take out can be provided by the AustralianATM

        /*
        loop -
        use as many of the largest note, then the next largest etc
        every time target amount is overshot, move to smaller note
        loop is also restricted by number of notes available

        if after all note sizes tried and no exact amount found, return false
        else return true

        maybe change from bool to return list of notes to be removed from atm?
        */

        int noteSizes[] = {100, 50, 20, 10};
        int withdrawTry = 0;

        for (int size: noteSizes){

            for (int noNotes = 0; noNotes < contents.get(size); noNotes++){
                withdrawTry += size;
                if (withdrawTry > withdrawAmmount){
                    withdrawTry -= size;
                }
            }

        }

        //if withdrawAmmount is the same as withdrawTry then the money can be taken out
        if (withdrawAmmount == withdrawTry){
            return true;
        }
        else{
            throw new NotEnoughCashException();
        }
    }

    public Withdrawal dispense(int amount) {
        return new Withdrawal();
    }

}
