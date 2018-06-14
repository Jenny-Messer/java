// Copyright (c) 2018 Travelex Ltd

package exceptions;

public class LoginFailedException extends RuntimeException {

    public LoginFailedException() {
        super("Pin or model.User Number entered incorrectly");
    }
}
