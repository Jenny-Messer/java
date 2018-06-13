// Copyright (c) 2018 Travelex Ltd

import java.util.Map;

public interface ATM {

    Withdrawal validDispense(int amount, Customer customer);

    Map<Integer, Integer> getContents();

    void setContents(Map<Integer, Integer> contents);


}
