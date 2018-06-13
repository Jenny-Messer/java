// Copyright (c) 2018 Travelex Ltd

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDataAccess {

    private static Map<Integer , User> users = new HashMap<>();

    public UserDataAccess() {

        Customer customer1 = new Customer(100, 1234, 12345);
        Customer customer2 = new Customer(0, 9876, 23456);

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
