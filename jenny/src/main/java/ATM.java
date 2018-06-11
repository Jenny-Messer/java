// Copyright (c) 2018 Travelex Ltd

public interface ATM {

    boolean validDispense(int amount);

    Withdrawal dispense(int amount);

}
