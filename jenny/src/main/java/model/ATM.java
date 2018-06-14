package model;// Copyright (c) 2018 Travelex Ltd

import java.math.BigDecimal;
import java.util.Map;

public interface ATM {

    String getCurrency();

    Withdrawal validDispense(BigDecimal withdrawAmount, Customer customer);

    Map<Integer, Integer> getContents();

    void setContents(Map<Integer, Integer> contents);


}
