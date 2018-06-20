package atm.model;// Copyright (c) 2018 Travelex Ltd

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BankEmployee extends User {

    public BankEmployee(@JsonProperty("pin") int pin,
                        @JsonProperty("userNumber") UUID userNumber){
        super(pin, userNumber);
    }
}
