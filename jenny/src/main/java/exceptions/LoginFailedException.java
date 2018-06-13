// Copyright (c) 2018 Travelex Ltd

package exceptions;

public class LoginFailedException extends RuntimeException {

    public LoginFailedException() {
        super("Pin or User Number entered incorrectly");
    }
}
