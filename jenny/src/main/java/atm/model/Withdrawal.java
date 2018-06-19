package atm.model;// Copyright (c) 2018 Travelex Ltd

import java.util.Map;

public class Withdrawal {
    private Map<Integer, Integer> contents;


    public Withdrawal() {
    }

    //contents of withdrawal - not atm.model.ATM
    public void setContents(Map<Integer, Integer> contents) {
        this.contents = contents;
    }

    public Map<Integer, Integer> getContents() {
        return contents;
    }
}
