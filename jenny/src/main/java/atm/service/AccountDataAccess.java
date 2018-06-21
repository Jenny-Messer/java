package atm.service;// Copyright (c) 2018 Travelex Ltd

import atm.entity.AccountEntity;
import atm.entity.CustomerEntity;
import atm.model.Account;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface AccountDataAccess {

    public void modifyAccount(UUID userId, UUID accountId, BigDecimal balance, String currency);

    public Optional<AccountEntity> getAccount(UUID customerId, UUID accountId);

    public void addAccount(UUID customerId, Account account);

}
