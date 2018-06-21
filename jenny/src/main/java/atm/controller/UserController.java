// Copyright (c) 2018 Travelex Ltd

package atm.controller;
import atm.entity.AccountEntity;
import atm.entity.CustomerEntity;
import atm.exceptions.UserNotFoundException;
import atm.model.Account;
import atm.model.BankEmployee;
import atm.model.Customer;
import atm.model.User;
import atm.service.AccountDataAccess;
import atm.service.CustomerDataAccess;
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
    private CustomerDataAccess customerDataAccess;

    @Autowired
    private AccountDataAccess accountDataAccess;

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
    remove accounts from customers

    once accounts are separate from users:
    modify user currently only changes pin, other thing to modify could be which accounts they are connected to
     */

    @RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable UUID customerId){

        Optional<CustomerEntity> userOpt = customerDataAccess.getCustomer(customerId);

        CustomerEntity customerEntity = userOpt.orElseThrow(UserNotFoundException::new);

        Customer customer = new Customer(customerEntity.getPin(), customerEntity.getId());

        return customer;

    }

    @RequestMapping(value = "/user/{userId}/account/{accountId}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable UUID userId, @PathVariable UUID accountId){

        Optional<AccountEntity> accountOpt = accountDataAccess.getAccount(userId, accountId);
        //TODO translate
        accountOpt.orElseThrow(RuntimeException::new);
        //throw exception if not found

        AccountEntity accountEntity = accountOpt.get();
        return new Account(accountEntity.getBalance(), accountEntity.getCurrency(), accountEntity.getAccountId());

    }

    //patch
    @RequestMapping(value = "/user/{userId}/account/{accountId}", method = RequestMethod.PATCH)
    public Account modifyAccount(@PathVariable UUID userId, @PathVariable UUID accountId, @RequestBody Account account){

        accountDataAccess.modifyAccount(userId, accountId, account.getBalance(), account.getCurrency());

        return getAccount(userId, accountId);

    }

    //patch
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.PATCH)
    public User modifyUser(@PathVariable UUID userId, @RequestBody Customer user){

        customerDataAccess.modifyCustomer(userId, user.getPin());

        return getCustomer(userId);

    }

    //post
    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public User addCustomer(@RequestBody Customer customer){


        UUID uuid = UUID.randomUUID();
        customer.setUserNumber(uuid);

        //TODO translate from Customer to CustomerEntity and send that
        CustomerEntity customer1 = new CustomerEntity();
        customer1.setId(uuid);
        customer1.setPin(customer.getPin());

        customerDataAccess.addCustomer(customer1);

        return getCustomer(uuid);

    }


    /*
    //post
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public User addEmployee(@RequestBody BankEmployee employee){

        UUID uuid = UUID.randomUUID();
        customerDataAccess.addEmployee(uuid, employee.getPin());

        return getCustomer(uuid);

    }
    */

    @RequestMapping(value = "/user/{userId}/account", method = RequestMethod.POST)
    public Account addAccount(@PathVariable UUID userId, @RequestBody Account account){

        UUID uuid = UUID.randomUUID();
        account.setAccountId(uuid);
        accountDataAccess.addAccount(userId, account);

        return getAccount(userId, uuid);

    }


}
