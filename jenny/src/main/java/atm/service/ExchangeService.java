// Copyright (c) 2018 Travelex Ltd

package atm.service;

import atm.model.Account;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public interface ExchangeService {

    BigDecimal exchange (BigDecimal exchangeAmount, UUID userId, UUID accountId, String targetCurrency);

    BigDecimal findRightConversion (BigDecimal exchangeAmount, String startCurrency, String targetCurrency);

    void getValueToRemoveFromBalanceInCorrectCurrency(String atmCurrency, Account account, Map<Integer, Integer>
            removedNotes);


    }
