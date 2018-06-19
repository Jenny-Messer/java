// Copyright (c) 2018 Travelex Ltd

package atm.exceptions;

public class LoginFailedException extends RuntimeException {

    public LoginFailedException() {
        super("Pin or atm.model.User Number entered incorrectly");
    }
}
