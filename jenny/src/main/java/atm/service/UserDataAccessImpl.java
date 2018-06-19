package atm.service;// Copyright (c) 2018 Travelex Ltd

import atm.exceptions.UserAlreadyExists;
import atm.exceptions.UserNotFoundException;
import atm.model.Account;
import atm.model.BankEmployee;
import atm.model.Customer;
import atm.model.User;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserDataAccessImpl implements UserDataAccess {

    private static Map<Integer , User> users = new HashMap<>();

    public UserDataAccessImpl() {

        Customer customer1 = new Customer(1234, 12345, new Account(new BigDecimal(100), "GBP"));
        Customer customer2 = new Customer(9876, 23456, new Account(BigDecimal.ZERO, "AUD"));

        BankEmployee employee1 = new BankEmployee(1234, 34567);
        BankEmployee employee2 = new BankEmployee(9876, 45678);

        users.put(customer1.getUserNumber(), customer1);
        users.put(customer2.getUserNumber(), customer2);
        users.put(employee1.getUserNumber(), employee1);
        users.put(employee2.getUserNumber(), employee2);


    }

    public Optional<User> getUser(Integer userId) {
        User user = users.get(userId);
        return user == null ? Optional.empty() : Optional.of(user);
    }

    public void addCustomer(int userId, int pin, BigDecimal balance, String currency){

        Customer customer = new Customer(pin, userId, new Account(balance, currency));

        Optional<User> userOpt = getUser(customer.getUserNumber());

        if (!userOpt.isPresent()){
            users.put(customer.getUserNumber(), customer);
        }
        else{
            throw new UserAlreadyExists();
        }
    }

    public void addEmployee(int userId, int pin){

        BankEmployee employee = new BankEmployee(pin, userId);

        Optional<User> userOpt = getUser(employee.getUserNumber());
        if (!userOpt.isPresent()){
            users.put(employee.getUserNumber(), employee);
        }
        else{
            throw new UserAlreadyExists();
        }
    }

    public void removeUser(int userId){

        Optional<User> userOpt = getUser(userId);
        User user = userOpt.orElseThrow(UserNotFoundException::new);

        users.remove(user.getUserNumber());
    }

    public void modifyUser(int userId, Integer newId, Integer newPin){

        Optional<User> userOpt = getUser(userId);

        if (!userOpt.isPresent()){
            throw new UserNotFoundException();
        }

        User user = userOpt.orElseThrow(UserNotFoundException::new);

        users.remove(user.getUserNumber());

        user.setPin(newPin);
        user.setUserNumber(newId);

        users.put(user.getUserNumber(), user);
    }

    public void modifyAccount(int userId, BigDecimal balance, String currency){

        Optional<User> userOpt = getUser(userId);

        if (!userOpt.isPresent()){
            throw new UserNotFoundException();
        }

        Customer customer = (Customer) userOpt.orElseThrow(UserNotFoundException::new);
        users.remove(customer.getUserNumber());

        customer.getAccount().setBalance(balance);
        customer.getAccount().setCurrency(currency);

        users.put(customer.getUserNumber(), customer);

    }

    public void modifyCustomer(int userId, Integer newId, Integer newPin, BigDecimal balance, String currency){
        modifyUser(userId, newId, newPin);
        modifyAccount(userId, balance, currency);
    }

}
