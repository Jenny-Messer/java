// Copyright (c) 2018 Travelex Ltd

package atm.exceptions;

public class NotEnoughCashException extends RuntimeException {

    public NotEnoughCashException() {
        super("Not enough cash for this transaction");
    }
}
