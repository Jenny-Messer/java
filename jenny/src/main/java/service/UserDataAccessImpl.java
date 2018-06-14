package service;// Copyright (c) 2018 Travelex Ltd

import model.BankEmployee;
import model.Customer;
import model.User;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDataAccessImpl implements UserDataAccess {

    private static Map<Integer , User> users = new HashMap<>();

    public UserDataAccessImpl() {

        Customer customer1 = new Customer(new BigDecimal(100), 1234, 12345, "GBP");
        Customer customer2 = new Customer(BigDecimal.ZERO, 9876, 23456, "USD");

        BankEmployee employee1 = new BankEmployee(1234, 34567);
        BankEmployee employee2 = new BankEmployee(9876, 45678);

        users.put(customer1.getUserNumber(), customer1);
        users.put(customer2.getUserNumber(), customer2);
        users.put(employee1.getUserNumber(), employee1);
        users.put(employee2.getUserNumber(), employee2);


    }

    public Optional<User> getUser(Integer userId) {
        User user = users.get(userId);
        System.out.println("Login successful");
        return user == null ? Optional.empty() : Optional.of(user);
    }



}
