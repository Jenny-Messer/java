// Copyright (c) 2018 Travelex Ltd

package atm.controller;
import atm.exceptions.UserNotFoundException;
import atm.model.Account;
import atm.model.BankEmployee;
import atm.model.Customer;
import atm.model.User;
import atm.service.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    private UserDataAccess userDataAccess;

    /*
    /user/{userId} - GET
    /account/{accountId} - GET
    /user/{userId}/account/{accountId} - PATCH
    /user/{userId}/ - PATCH
    /customer/{userId}/ - POST
    /employee/{userId}/ - POST
     */

    /*
    TODO
    add customer/employee/account broken
    slightly change urls
    remove accounts from customers
     */

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable UUID userId){

        Optional<User> userOpt = userDataAccess.getUser(userId);
        User user = userOpt.orElseThrow(UserNotFoundException::new);

        return user;

    }

    @RequestMapping(value = "/user/{userId}/account/{accountId}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable UUID userId, @PathVariable UUID accountId){

        Optional<User> userOpt = userDataAccess.getUser(userId);
        Customer customer = (Customer) userOpt.orElseThrow(UserNotFoundException::new);

        Account account = userDataAccess.getAccount(customer, accountId);

        return account;

    }

    //patch
    @RequestMapping(value = "/user/{userId}/account/{accountId}", method = RequestMethod.PATCH)
    public Account modifyAccount(@PathVariable UUID userId, @PathVariable UUID accountId, @RequestBody Account account){

        //TODO modify account holder(s) ??

        userDataAccess.modifyAccount(userId, accountId, account.getBalance(), account.getCurrency());

        return getAccount(userId, accountId);

    }

    //patch
    @RequestMapping(value = "/user/{userId}/", method = RequestMethod.PATCH)
    public User modifyUser(@PathVariable UUID userId, @RequestBody User user){

        userDataAccess.modifyUser(userId, user.getPin());

        return getUser(userId);

    }

    //post
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public User addCustomer(@RequestBody Customer customer){


        UUID uuid = UUID.randomUUID();
        customer.setUserNumber(uuid);
        userDataAccess.addCustomer(customer);

        return getUser(uuid);

    }


    //post
    @RequestMapping(value = "/employee/{userId}/", method = RequestMethod.POST)
    public User addEmployee(@PathVariable UUID userId, @RequestBody BankEmployee employee){

        UUID uuid = UUID.randomUUID();
        userDataAccess.addEmployee(userId, employee.getPin());

        return getUser(uuid);

    }

    @RequestMapping(value = "/account/{userId}/", method = RequestMethod.POST)
    public Account addAccount(@PathVariable UUID userId, @RequestBody Account account){

        UUID uuid = UUID.randomUUID();
        account.setAccountId(uuid);
        userDataAccess.addAccount(userId, account);


        return getAccount(userId, uuid);

    }




}
