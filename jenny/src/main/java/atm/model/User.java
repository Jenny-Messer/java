package atm.model;// Copyright (c) 2018 Travelex Ltd

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Customer.class, name = "Customer"),
        @JsonSubTypes.Type(value = BankEmployee.class, name = "Employee")
})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class User {

    private int userNumber;
    private int pin;

    @JsonCreator
    public User(@JsonProperty("pin") int pin,
                @JsonProperty("userNumber") int userNumber) {
        this.pin = pin;
        this.userNumber = userNumber;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

}
