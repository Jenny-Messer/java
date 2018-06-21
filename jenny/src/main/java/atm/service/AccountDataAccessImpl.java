package atm.service;// Copyright (c) 2018 Travelex Ltd

import atm.entity.AccountEntity;
import atm.repository.AccountRepository;
import atm.exceptions.UserNotFoundException;
import atm.model.Account;
import atm.model.Customer;
import atm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountDataAccessImpl implements AccountDataAccess {

    @Autowired
    private AccountRepository accountRepository;


    public void modifyAccount(UUID userId, UUID accountId, BigDecimal balance, String currency){


        //check user has account with accountId
        //get that account
        //modify it

        Optional<AccountEntity> accountOpt = getAccount(userId, accountId);


        //TODO make a not found account exception
        AccountEntity account = accountOpt.orElseThrow(RuntimeException::new);

        account.setBalance(balance);
        account.setCurrency(currency);

        accountRepository.save(account);

    }

    // can probably delete this?
    public Optional<AccountEntity> getAccount(UUID customerId, UUID accountId){

        Optional<AccountEntity> accountEntity = accountRepository.findByAccountIdAndCustomerId(accountId, customerId);
        return accountEntity; //accountEntity == null ? Optional.empty() : Optional.of(accountEntity);
    }

    public void addAccount(UUID customerId, Account account){

        Optional<AccountEntity> AccountOpt = getAccount(customerId, account.getAccountId());

        if (!AccountOpt.isPresent()){
            throw new UserNotFoundException(); //TODO new account already exist
        }

        AccountEntity accountEntity = AccountOpt.orElseThrow(UserNotFoundException::new);

        accountRepository.save(accountEntity);

    }

}
