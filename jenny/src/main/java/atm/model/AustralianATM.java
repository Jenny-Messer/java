package atm.model;// Copyright (c) 2018 Travelex Ltd

import atm.exceptions.NotEnoughCashInAtmException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AustralianATM implements ATM {

    private String currency;

    private Map<Integer , Integer> contents; //final?

    public AustralianATM(Map<Integer, Integer> contents) {
        this.contents = contents;
        this.currency = "AUD";
    }

    public Map<Integer, Integer> getContents() {
        return contents;
    }

    public String getCurrency() {
        return currency;
    }

    public void setContents(Map<Integer, Integer> contents) {
        this.contents = contents;
    }

    public Withdrawal validDispense(BigDecimal withdrawAmount, Customer customer) throws NotEnoughCashInAtmException {

        //withdraw request must be in same currency as ATM

        BigDecimal withdrawTry = new BigDecimal(0);

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

    private Map<Integer, Integer> getNotesToRemove(BigDecimal withdrawAmount, BigDecimal withdrawTry) {
        Map<Integer, Integer> removedNotes = new HashMap<Integer, Integer>();
        removedNotes.put(10, 0);
        removedNotes.put(20, 0);
        removedNotes.put(50, 0);
        removedNotes.put(100, 0);


        for (Integer noteSize : getNotesInOrder()) {

            for (int noNotes = 0; noNotes < contents.get(noteSize); noNotes++){
                withdrawTry = withdrawTry.add(new BigDecimal(noteSize));
                removedNotes.put(noteSize, removedNotes.get(noteSize)+1);
                if (0 < withdrawTry.compareTo(withdrawAmount)){ //>
                    withdrawTry = withdrawTry.subtract(new BigDecimal(noteSize));
                    removedNotes.put(noteSize, removedNotes.get(noteSize)-1);
                }
            }

        }

        if (!withdrawAmount.equals(withdrawTry)) {
            throw new NotEnoughCashInAtmException();
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
