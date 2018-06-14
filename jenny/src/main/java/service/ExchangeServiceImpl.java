// Copyright (c) 2018 Travelex Ltd

package service;

    /*
    getRate
    calculate exchanged in user's currency
    subtract from user's balance
    return target currency
     */

//provide with atm and userdataccess

import exceptions.UserNotFoundException;
import model.Customer;
import model.User;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class ExchangeServiceImpl implements ExchangeService {

    private UserDataAccess userDataAccess;
    private BigDecimal GbpToAud = new BigDecimal(1.78);

    public ExchangeServiceImpl(UserDataAccess userDataAccess) {
        this.userDataAccess = userDataAccess;
    }

    //user currency to given
    public BigDecimal exchange(BigDecimal exchangeAmount, int userId, String targetCurrency) {

        //get user's currency
        Optional<User> userOpt = userDataAccess.getUser(userId);
        Customer customer = (Customer) userOpt.orElseThrow(UserNotFoundException::new);

        String customerCurrency = customer.getCurrency();

        return findRightConversion(exchangeAmount, customerCurrency,targetCurrency);

    }

    public BigDecimal findRightConversion (BigDecimal exchangeAmount, String startCurrency, String targetCurrency){

        BigDecimal converted = new BigDecimal(0);
        //find right conversion
        if (startCurrency.equals("GBP") && targetCurrency.equals("AUD")){
            converted = exchangeAmount.multiply(GbpToAud);
        }
        else if (startCurrency.equals("AUD") && targetCurrency.equals("GBP")){
            converted = exchangeAmount.divide(GbpToAud,BigDecimal.ROUND_HALF_UP);
        }

        //return exchanged value
        return converted;
    }

    public void getValueToRemoveFromBalanceInCorrectCurrency(String atmCurrency, int customerId, Map<Integer, Integer> removedNotes){

        Optional<User> userOpt = userDataAccess.getUser(customerId);
        Customer customer = (Customer) userOpt.orElseThrow(UserNotFoundException::new);

        int total = 0;
        for (Map.Entry<Integer, Integer> noteEntry : removedNotes.entrySet()) {
            total += noteEntry.getKey() * noteEntry.getValue();
        }

        if (atmCurrency.equals(customer.getCurrency())){
            customer.setBalance(customer.getBalance().subtract(new BigDecimal(total)));
        }
        else{
            //convert total to user's currency then - from balance
            //findRightConversion (BigDecimal exchangeAmount, String startCurrency, String targetCurrency)
            BigDecimal newTotal = findRightConversion(new BigDecimal(total) ,atmCurrency, customer.getCurrency());

            customer.setBalance(customer.getBalance().subtract(newTotal));

        }

    }


}
