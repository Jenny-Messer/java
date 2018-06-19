// Copyright (c) 2018 Travelex Ltd

package atm.exceptions;

public class UserAlreadyExists extends RuntimeException {

    public UserAlreadyExists() {
        super("User already exists");
    }
}
