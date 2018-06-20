// Copyright (c) 2018 Travelex Ltd

package atm.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account {

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @JsonCreator
    public Account(@JsonProperty("balance") BigDecimal balance,
                   @JsonProperty("currency") String currency,
                   @JsonProperty("accountId") UUID accountId) {
        this.balance = balance;
        this.currency = currency;
        this.accountId = accountId;
    }

    private BigDecimal balance;

    private String currency;

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    private UUID accountId;

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
