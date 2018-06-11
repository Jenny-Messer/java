// Copyright (c) 2018 Travelex Ltd

import java.util.Map;

public class Withdrawal {
    private Map<Integer, Integer> contents;
    private String receipt;

    public Withdrawal() {
    }

    public void setContents(Map<Integer, Integer> contents) {
        this.contents = contents;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }
}
