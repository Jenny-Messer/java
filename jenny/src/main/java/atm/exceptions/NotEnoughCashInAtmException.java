// Copyright (c) 2018 Travelex Ltd

package atm.exceptions;

public class NotEnoughCashInAtmException extends RuntimeException {

    public NotEnoughCashInAtmException() {
        super("Not enough cash in ATM for this transaction");
    }
}
