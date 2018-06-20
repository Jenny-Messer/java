package atm.model;// Copyright (c) 2018 Travelex Ltd

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer extends User {

    private Account account;

    @JsonCreator
    public Customer(@JsonProperty("pin") int pin,
                    @JsonProperty("customerNumber") int customerNumber,
                    @JsonProperty("account") Account account) {
        super(pin, customerNumber);

        this.account = account;
    }

    public Account getAccount() {
        return account;
    } //TODO change when user can have multiple accounts

}
