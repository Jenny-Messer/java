package atm.service;// Copyright (c) 2018 Travelex Ltd

import atm.model.User;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserDataAccess {
    public Optional<User> getUser(Integer userId);

    public void addCustomer(int userId, int pin, BigDecimal balance, String currency);

    public void addEmployee(int userId, int pin);

    public void removeUser(int userId);

    public void modifyUser(int userId, Integer newId, Integer newPin);

    public void modifyAccount(int userId, BigDecimal balance, String currency);

}
