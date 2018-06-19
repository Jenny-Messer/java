// Copyright (c) 2018 Travelex Ltd

package atm.model;

import java.math.BigDecimal;

public class Account {

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Account(BigDecimal balance, String currency) {
        this.balance = balance;
        this.currency = currency;
    }

    private BigDecimal balance;

    private String currency;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

}
