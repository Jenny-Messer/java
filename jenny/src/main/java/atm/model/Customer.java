package atm.model;// Copyright (c) 2018 Travelex Ltd

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer extends User {

    private Map<UUID , Account> accounts = new HashMap<>();

    @JsonCreator
    public Customer(@JsonProperty("pin") int pin,
                    @JsonProperty("customerNumber") UUID customerNumber,
                    @JsonProperty("account") Account account) {
        super(pin, customerNumber);

        this.accounts.put(account.getAccountId(), account);
    }

    public Map<UUID, Account> getAccounts() {
        return this.accounts;
    }

    public void addAccount(Account account){
        this.accounts.put(account.getAccountId(), account);
    }

}
