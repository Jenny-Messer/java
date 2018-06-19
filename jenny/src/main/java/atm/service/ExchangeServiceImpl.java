// Copyright (c) 2018 Travelex Ltd

package atm.service;

    /*
    getRate
    calculate exchanged in user's currency
    subtract from user's balance
    return target currency
     */

//provide with atm and userdataccess

import atm.exceptions.UserNotFoundException;
import atm.model.Customer;
import atm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
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

        String customerCurrency = customer.getAccount().getCurrency();

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

        if (atmCurrency.equals(customer.getAccount().getCurrency())){
            customer.getAccount().setBalance(customer.getAccount().getBalance().subtract(new BigDecimal(total)));
        }
        else{
            //convert total to user's currency then - from balance
            //findRightConversion (BigDecimal exchangeAmount, String startCurrency, String targetCurrency)
            BigDecimal newTotal = findRightConversion(new BigDecimal(total) ,atmCurrency, customer.getAccount().getCurrency());

            customer.getAccount().setBalance(customer.getAccount().getBalance().subtract(newTotal));

        }

    }


}
