// Copyright (c) 2018 Travelex Ltd

package atm.exceptions;

public class NotEnoughCashInAccountException extends RuntimeException {

    public NotEnoughCashInAccountException() {
        super("Not enough cash in account for this transaction");
    }
}
