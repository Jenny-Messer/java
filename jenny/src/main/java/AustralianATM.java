// Copyright (c) 2018 Travelex Ltd

import exceptions.NotEnoughCashException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AustralianATM implements ATM {

    private Map<Integer , Integer> contents;

    public AustralianATM(Map<Integer, Integer> contents) {
        this.contents = contents;
    }

    public Map<Integer, Integer> getContents() {
        return contents;
    }

    public Withdrawal validDispense(int withdrawAmount) throws NotEnoughCashException {

        //checks if the money the user wants to take out can be provided by the AustralianATM

        /*
        use as many of the largest note as possible, then the next largest etc
        every time target amount is overshot, move to smaller note

        if after all notes of all sizes tried and the exact amount not reached, throw exception
        else return true
        */

        int withdrawTry = 0;


        Map<Integer, Integer> removedNotes = getNotesToRemove(withdrawAmount, withdrawTry);

        removeNotesFromContents(removedNotes);

        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setContents(removedNotes);
        return withdrawal;


    }

    private void removeNotesFromContents(Map<Integer, Integer> removedNotes){
        contents.keySet()
                .forEach(noteSize -> contents.put(noteSize, contents.get(noteSize) - removedNotes.get(noteSize)));

    }

    private Map<Integer, Integer> getNotesToRemove(int withdrawAmount, int withdrawTry) {
        Map<Integer, Integer> removedNotes = new HashMap<Integer, Integer>();
        removedNotes.put(10, 0);
        removedNotes.put(20, 0);
        removedNotes.put(50, 0);
        removedNotes.put(100, 0);


        for (Integer noteSize : getNotesInOrder()) {

            for (int noNotes = 0; noNotes < contents.get(noteSize); noNotes++){
                withdrawTry += noteSize;
                removedNotes.put(noteSize, removedNotes.get(noteSize)+1);
                if (withdrawTry > withdrawAmount){
                    withdrawTry -= noteSize;
                    removedNotes.put(noteSize, removedNotes.get(noteSize)-1);
                }
            }

        }

        if (withdrawAmount != withdrawTry) {
            throw new NotEnoughCashException();
        }

        return removedNotes;
    }

    private List<Integer> getNotesInOrder() {
        List<Integer> collect = new ArrayList<>(contents.keySet());
        collect.sort(Collections.reverseOrder());
        return collect;
    }

    public void refillAtm(Map<Integer , Integer> cashToAdd){

        //could add a max capacity thing?

        contents.keySet()
                .forEach(noteSize -> contents.put(noteSize, contents.get(noteSize) + cashToAdd.get(noteSize)));

    }



}
