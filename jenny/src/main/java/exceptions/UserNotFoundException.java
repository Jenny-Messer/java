// Copyright (c) 2018 Travelex Ltd

package exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("model.User does not exist");
    }
}
