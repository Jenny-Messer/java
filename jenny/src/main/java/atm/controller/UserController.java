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

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable Integer userId){

        Optional<User> userOpt = userDataAccess.getUser(userId);
        User user = userOpt.orElseThrow(UserNotFoundException::new);

        return user;

    }

    @RequestMapping(value = "/account/{accountId}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable Integer accountId){

        //TODO once users have multiple accounts a real account id lookup need to me made
        Optional<User> userOpt = userDataAccess.getUser(accountId);
        Customer customer = (Customer) userOpt.orElseThrow(UserNotFoundException::new);

        Account account = customer.getAccount();

        return account;

    }

    //patch
    @RequestMapping(value = "/user/{userId}/account/{accountId}", method = RequestMethod.PATCH)
    public Account modifyAccount(@PathVariable int userId, @PathVariable int accountId, @RequestBody Account account){

        userDataAccess.modifyAccount(userId, accountId, account.getBalance(), account.getCurrency());

        return getAccount(accountId);

    }

    //patch
    @RequestMapping(value = "/user/{userId}/", method = RequestMethod.PATCH)
    public User modifyUser(@PathVariable Integer userId, @RequestBody User user){

        userDataAccess.modifyUser(userId, user.getPin());

        return getUser(userId);

    }

    @RequestMapping(value = "/customer/{userId}/", method = RequestMethod.PATCH)
    public User modifyCustomer(@PathVariable Integer userId, @RequestBody Customer customer){

        userDataAccess.modifyUser(userId, customer.getPin());

        return getUser(userId);

    }

    //not needed now @RequestBody User works??
    /*

    @RequestMapping(value = "/employee/{userId}/", method = RequestMethod.PATCH)
    public User modifyCustomer(@PathVariable Integer userId, @RequestBody BankEmployee employee){

        userDataAccess.modifyUser(userId, employee.getPin());

        return getUser(userId);

    }

    //modifyCustomer(int userId, Integer newId, Integer newPin, BigDecimal balance, String currency)

    //post
    @RequestMapping(value = "/customer/{userId}/", method = RequestMethod.POST)
    public User addCustomer(@PathVariable int userId, @RequestBody Customer customer){

        userDataAccess.addCustomer(userId, customer.getPin(), customer.getAccount().getBalance(), customer.getAccount().getCurrency());

        return getUser(userId);

    }

    */

    //post
    @RequestMapping(value = "/employee/{userId}/", method = RequestMethod.POST)
    public User addEmployee(@PathVariable int userId, @RequestBody BankEmployee employee){

        userDataAccess.addEmployee(userId, employee.getPin());

        return getUser(userId);

    }

}
