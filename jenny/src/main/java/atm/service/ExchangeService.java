// Copyright (c) 2018 Travelex Ltd

package atm.service;

import java.math.BigDecimal;
import java.util.Map;

public interface ExchangeService {

    BigDecimal exchange(BigDecimal exchangeAmount, int userId, String targetCurrency);

    BigDecimal findRightConversion (BigDecimal exchangeAmount, String startCurrency, String targetCurrency);

    void getValueToRemoveFromBalanceInCorrectCurrency(String atmCurrency, int customerId, Map<Integer, Integer> removedNotes);



    }
