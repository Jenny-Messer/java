// Copyright (c) 2018 Travelex Ltd

package atm.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User does not exist");
    }
}
