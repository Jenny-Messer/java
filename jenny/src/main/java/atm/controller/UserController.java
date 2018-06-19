// Copyright (c) 2018 Travelex Ltd

package atm.controller;

import atm.exceptions.UserNotFoundException;
import atm.model.User;
import atm.service.UserDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserDataAccess userDataAccess;

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable Integer userId){

        Optional<User> userOpt = userDataAccess.getUser(userId);
        User user = userOpt.orElseThrow(UserNotFoundException::new);

        return user;

    }

    //user, account, customer
    @RequestMapping(value = "/user/{userId}/newId/{newId}/newPin/{newPin}", method = RequestMethod.GET)
    public User modifyUser(@PathVariable Integer userId, @PathVariable Integer newId, @PathVariable Integer newPin){

        userDataAccess.modifyUser(userId, newId, newPin);

        return getUser(newId);

    }

    @RequestMapping(value = "/user/{userId}/balance/{balance}/currency/{currency}", method = RequestMethod.GET)
    public User modifyAccount(@PathVariable int userId, @PathVariable BigDecimal balance,
                              @PathVariable String currency){

        userDataAccess.modifyAccount(userId, balance, currency);

        return getUser(userId);

    }

    //modifyCustomer(int userId, Integer newId, Integer newPin, BigDecimal balance, String currency)


    @RequestMapping(value = "/newUser/{userId}/pin/{pin}/balance/{balance}/currency/{currency}", method = RequestMethod.GET)
    public User addCustomer(@PathVariable int userId, @PathVariable int pin, @PathVariable BigDecimal balance,
                            @PathVariable String currency){

        userDataAccess.addCustomer(userId, pin, balance, currency);

        return getUser(userId);

    }

    @RequestMapping(value = "/newUser/{userId}/pin/{pin}/", method = RequestMethod.GET)
    public User addEmployee(@PathVariable int userId, @PathVariable int pin){

        userDataAccess.addEmployee(userId, pin);

        return getUser(userId);

    }

}
