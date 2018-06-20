package atm.service;// Copyright (c) 2018 Travelex Ltd

import atm.model.Account;
import atm.model.Customer;
import atm.model.User;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface UserDataAccess {
    public Optional<User> getUser(UUID userId);

    public void addCustomer(Customer customer);

    public void addEmployee(UUID userId, int pin);

    public void removeUser(UUID userId);

    public void modifyUser(UUID userId, Integer newPin);

    public void modifyAccount(UUID userId, UUID accountId, BigDecimal balance, String currency);

    public Account getAccount(Customer customer, UUID accountId);

    public void addAccount(UUID customerId, Account account);

}
