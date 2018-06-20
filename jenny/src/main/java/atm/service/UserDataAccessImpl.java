package atm.service;// Copyright (c) 2018 Travelex Ltd

import atm.exceptions.UserAlreadyExistsException;
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
import java.util.UUID;

@Service
public class UserDataAccessImpl implements UserDataAccess {

    private static Map<UUID , User> users = new HashMap<>();

    public UserDataAccessImpl() {

        /*
        UUID IDs[] = new UUID[6];

        for (int i = 0; i<6; i++){
            IDs[i] = UUID.randomUUID();
            System.out.println("ID "+ i + " " + IDs[i]);
        }

        Customer customer1 = new Customer(1234, IDs[0], new Account(new BigDecimal(100), "GBP", IDs[1] ));
        Customer customer2 = new Customer(9876, IDs[2], new Account(BigDecimal.ZERO, "AUD", IDs[3]));

        BankEmployee employee1 = new BankEmployee(1234, IDs[4]);
        BankEmployee employee2 = new BankEmployee(9876, IDs[5]);
        */

        Customer customer1 = new Customer(1234, UUID.fromString("d67aa1ae-0332-4aeb-bc1f-fba2967e7bda"), new Account(new BigDecimal(100), "GBP", UUID.fromString("adf283b8-0e2c-415e-9090-43bf467f3d2f") ));
        Customer customer2 = new Customer(9876, UUID.fromString("fad4ae65-edb2-4bcc-ac88-537f50fec2fb"), new Account(BigDecimal.ZERO, "AUD", UUID.fromString("cd72489b-f2c4-4eda-b492-96169b89bb27")));

        BankEmployee employee1 = new BankEmployee(1234, UUID.fromString("b967ebe3-ff54-4671-9270-7bedbd594cde"));
        BankEmployee employee2 = new BankEmployee(9876, UUID.fromString("e6ea1563-5492-415f-9e18-77f5831ee3b7"));

        users.put(customer1.getUserNumber(), customer1);
        users.put(customer2.getUserNumber(), customer2);
        users.put(employee1.getUserNumber(), employee1);
        users.put(employee2.getUserNumber(), employee2);

        /*
        ID 0 d67aa1ae-0332-4aeb-bc1f-fba2967e7bda   customer ID
        ID 1 adf283b8-0e2c-415e-9090-43bf467f3d2f   account ID
        ID 2 fad4ae65-edb2-4bcc-ac88-537f50fec2fb   customer ID
        ID 3 cd72489b-f2c4-4eda-b492-96169b89bb27   account ID
        ID 4 b967ebe3-ff54-4671-9270-7bedbd594cde   employee ID
        ID 5 e6ea1563-5492-415f-9e18-77f5831ee3b7   employee ID
         */



    }

    public Optional<User> getUser(UUID userId) {
        User user = users.get(userId);
        return user == null ? Optional.empty() : Optional.of(user);
    }

    public void addCustomer(Customer customer){

        Optional<User> userOpt = getUser(customer.getUserNumber());

        if (!userOpt.isPresent()){
            users.put(customer.getUserNumber(), customer);
        }
        else{
            throw new UserAlreadyExistsException();
        }
    }

    public void addEmployee(UUID userId, int pin){

        BankEmployee employee = new BankEmployee(pin, userId);

        Optional<User> userOpt = getUser(employee.getUserNumber());
        if (!userOpt.isPresent()){
            users.put(employee.getUserNumber(), employee);
        }
        else{
            throw new UserAlreadyExistsException();
        }
    }

    public void removeUser(UUID userId){

        Optional<User> userOpt = getUser(userId);
        User user = userOpt.orElseThrow(UserNotFoundException::new);

        users.remove(user.getUserNumber());
    }

    public void modifyUser(UUID userId, Integer newPin){

        Optional<User> userOpt = getUser(userId);

        if (!userOpt.isPresent()){
            throw new UserNotFoundException();
        }

        User user = userOpt.orElseThrow(UserNotFoundException::new);

        users.remove(user.getUserNumber());

        user.setPin(newPin);

        users.put(user.getUserNumber(), user);
    }

    public void modifyAccount(UUID userId, UUID accountId, BigDecimal balance, String currency){

        Optional<User> userOpt = getUser(userId);

        if (!userOpt.isPresent()){
            throw new UserNotFoundException();
        }

        Customer customer = (Customer) userOpt.orElseThrow(UserNotFoundException::new);

        //check user has account with accountId
        //get that account
        //modify it

        Account account = getAccount(customer, accountId);

        account.setBalance(balance);
        account.setCurrency(currency);

        users.replace(customer.getUserNumber(), customer);

    }

    public Account getAccount(Customer customer, UUID accountId){

        Map<UUID, Account> accounts = customer.getAccounts();

        Account account = accounts.get(accountId);

        return account;
    }

    public void modifyCustomer(UUID userId, Integer newPin, BigDecimal balance, String currency){
        modifyUser(userId, newPin);
        modifyAccount(userId, userId, balance, currency);
    }

    public void addAccount(UUID customerId, Account account){

        Optional<User> userOpt = getUser(customerId);

        if (!userOpt.isPresent()){
            throw new UserNotFoundException();
        }

        Customer customer = (Customer) userOpt.orElseThrow(UserNotFoundException::new);

        customer.addAccount(account);

    }

}
