// Copyright (c) 2018 Travelex Ltd

package service;

import java.math.BigDecimal;

public interface ExchangeService {


    BigDecimal exchange(BigDecimal exchangeAmount, int userId, String targetCurrency);



}
