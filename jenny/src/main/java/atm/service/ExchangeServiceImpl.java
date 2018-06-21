// Copyright (c) 2018 Travelex Ltd

package atm.service;

    /*
    getRate
    calculate exchanged in user's currency
    subtract from user's balance
    return target currency
     */

//provide with atm and userdataccess

import atm.entity.AccountEntity;
import atm.entity.CustomerEntity;
import atm.exceptions.UserNotFoundException;
import atm.model.Account;
import atm.model.Customer;
import atm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private AccountDataAccess accountDataAccess;

    private BigDecimal GbpToAud = new BigDecimal(1.78);

    //user currency to given
    public BigDecimal exchange(BigDecimal exchangeAmount, UUID userId, UUID accountId, String targetCurrency) {

        //get user's currency
        Optional<AccountEntity> userOpt = accountDataAccess.getAccount(userId, accountId);

        //
        AccountEntity accountEntity = userOpt.orElseThrow(RuntimeException::new);

        //TODO fix this
        return findRightConversion(exchangeAmount, accountEntity.getCurrency() ,targetCurrency);

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

    public void getValueToRemoveFromBalanceInCorrectCurrency(String atmCurrency, Account account, Map<Integer, Integer>
            removedNotes){


        int total = 0;
        for (Map.Entry<Integer, Integer> noteEntry : removedNotes.entrySet()) {
            total += noteEntry.getKey() * noteEntry.getValue();
        }

        if (atmCurrency.equals(account.getCurrency())){
            account.setBalance(account.getBalance().subtract(new BigDecimal(total)));
        }
        else{
            //convert total to user's currency then - from balance
            //findRightConversion (BigDecimal exchangeAmount, String startCurrency, String targetCurrency)
            BigDecimal newTotal = findRightConversion(new BigDecimal(total) ,atmCurrency, account.getCurrency());

            account.setBalance(account.getBalance().subtract(newTotal));

        }

    }


}
